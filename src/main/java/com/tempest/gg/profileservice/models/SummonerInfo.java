package com.tempest.gg.profileservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tempest.gg.profileservice.models.riot.ChampStatsEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "summonerInfo")
public class SummonerInfo {

    @Id
    private String summonerName;
    private String mainRole;

    @JsonIgnore
    private CircularFifoQueue<String> roles = new CircularFifoQueue<>(100);

    @JsonIgnore
    private CircularFifoQueue<ChampStatsEntry> statsEntries = new CircularFifoQueue<>(100);

    private Map<String, SummonerChampInfo> champInfo = new HashMap<>();

    @Async
    public void updateSummonerInfo() {
        updateMainRole();
        updateChampInfo();
    }


    private void updateMainRole() {
        this.mainRole = roles.
                stream().collect(Collectors.groupingBy(entry -> entry, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }

    private void updateChampInfo() {
        statsEntries.forEach(entry -> {
            if(champInfo == null) {
                return;
            }
            SummonerChampInfo summonerChampInfo = champInfo.get(entry.getChampionName());
            if (summonerChampInfo != null) {
                summonerChampInfo.updateStats(entry);
            } else {
                champInfo.put(entry.getChampionName(), new SummonerChampInfo(
                        entry.getChampionName(),
                        entry.getKDA(),
                        entry.getKills().floatValue(),
                        entry.getDeaths().floatValue(),
                        entry.getAssists().floatValue(),
                        entry.getWin() ? (float) 100 : (float) 0,
                        entry.getCpm(),
                        entry.getWin() ? (long) 1 : (long) 0
                ));
            }
        });
    }

}
