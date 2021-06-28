package com.tempest.gg.profileservice.services;

import com.tempest.gg.profileservice.clients.SummonerProfileClient;
import com.tempest.gg.profileservice.models.Ranking;
import com.tempest.gg.profileservice.models.SummonerProfile;
import com.tempest.gg.profileservice.models.riot.RiotMatch;
import com.tempest.gg.profileservice.models.riot.RiotSummonerProfile;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class SummonerProfileService {

    private final SummonerProfileClient summonerProfileClient;
    private final RankingService rankingService;
    private final MatchHistoryService matchHistoryService;

    @Value("${riot.api.datadragon}")
    private String dataDragonUrl;

    public SummonerProfileService(SummonerProfileClient summonerProfileClient, RankingService rankingService, MatchHistoryService matchHistoryService) {
        this.summonerProfileClient = summonerProfileClient;
        this.rankingService = rankingService;
        this.matchHistoryService = matchHistoryService;
    }

    @SneakyThrows
    public SummonerProfile getSummonerProfile(String summonerName) {

        RiotSummonerProfile summoner = summonerProfileClient.getSummonerByName(summonerName);

        Future<List<Ranking>> rankings = rankingService.getRankings(summoner);
        Future<List<String>> matchHistory = matchHistoryService.getMatchHistory(summoner.getPuuid());


        return SummonerProfile.builder()
                .id(summoner.getId())
                .name(summoner.getName())
                .position("Support")
                .rankings(rankings.get())
                .matches(matchHistory.get())
                .profilePictureUrl(dataDragonUrl + "/img/profileicon/" + summoner.getProfileIconId() + ".png")
                .build();
    }
}
