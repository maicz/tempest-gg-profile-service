package com.tempest.gg.profileservice.clients;

import com.tempest.gg.profileservice.models.riot.RiotMatch;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "matchHistory", url = "${riot.api.url.v5}")
public interface MatchHistoryClient {

    @GetMapping("match/v5/matches/by-puuid/{playerUUID}/ids?queue=400")
    public List<String> getMatchHistory(@PathVariable("playerUUID") String playerUUID);

    @GetMapping("match/v5/matches/{matchId}")
    public RiotMatch getMatchById(@PathVariable("matchId") String matchId);

    @GetMapping("match/v5/matches/{matchId}/timeline")
    public String getMatchTimeLineByMatchId(@PathVariable("matchId") String matchId);

}
