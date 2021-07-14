package com.tempest.gg.profileservice.services;

import com.tempest.gg.profileservice.clients.SummonerProfileClient;
import com.tempest.gg.profileservice.models.Ranking;
import com.tempest.gg.profileservice.models.SummonerProfile;
import com.tempest.gg.profileservice.models.riot.RiotSummonerProfile;
import com.tempest.gg.profileservice.repositories.PlayerRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
@Slf4j
public class SummonerProfileService {

    private final SummonerProfileClient summonerProfileClient;
    private final RankingService rankingService;
    private final MatchHistoryService matchHistoryService;
    private final PlayerRepository playerRepository;

    @Value("${riot.api.datadragon}")
    private String dataDragonUrl;

    public SummonerProfileService(
            SummonerProfileClient summonerProfileClient,
            RankingService rankingService,
            MatchHistoryService matchHistoryService, PlayerRepository playerRepository) {

        this.summonerProfileClient = summonerProfileClient;
        this.rankingService = rankingService;
        this.matchHistoryService = matchHistoryService;
        this.playerRepository = playerRepository;
    }

    @SneakyThrows
    public SummonerProfile getSummonerProfile(String summonerName) {

        SummonerProfile cachedProfile = playerRepository.findByNameIgnoreCase(summonerName);

        if (cachedProfile != null) {
            return cachedProfile;
        }

        RiotSummonerProfile summoner = summonerProfileClient.getSummonerByName(summonerName);
        Future<List<Ranking>> rankings = rankingService.getRankings(summoner);
        Future<List<String>> matchHistory = matchHistoryService.getMatchHistory(summoner.getPuuid());

        SummonerProfile profile =
                SummonerProfile.builder()
                        .id(summoner.getId())
                        .name(summoner.getName())
                        .position("Support")
                        .rankings(rankings.get())
                        .matches(matchHistory.get())
                        .profilePictureUrl(dataDragonUrl + "/img/profileicon/" + summoner.getProfileIconId() + ".png")
                        .build();

        playerRepository.save(profile);
        return profile;
    }

}
