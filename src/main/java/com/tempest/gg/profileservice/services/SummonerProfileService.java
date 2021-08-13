package com.tempest.gg.profileservice.services;

import com.tempest.gg.profileservice.clients.SummonerProfileClient;
import com.tempest.gg.profileservice.models.Ranking;
import com.tempest.gg.profileservice.models.SummonerProfile;
import com.tempest.gg.profileservice.models.riot.RiotSummonerProfile;
import com.tempest.gg.profileservice.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@Service
@Slf4j
@RequiredArgsConstructor
public class SummonerProfileService {

    private final SummonerProfileClient summonerProfileClient;
    private final RankingService rankingService;
    private final MatchHistoryService matchHistoryService;
    private final PlayerRepository playerRepository;

    @Value("${riot.api.datadragon}")
    private String dataDragonUrl;

    @SneakyThrows
    public SummonerProfile getSummonerProfile(String summonerName) {

        Optional<SummonerProfile> cachedProfile = playerRepository.findByNameIgnoreCase(summonerName);

        if (cachedProfile.isPresent()) {
            return cachedProfile.get();
        }

        RiotSummonerProfile summoner = summonerProfileClient.getSummonerByName(summonerName);
        Future<List<Ranking>> rankings = rankingService.getRankings(summoner);
        Future<List<String>> matchHistory = matchHistoryService.getMatchHistory(summoner.getPuuid());
        matchHistoryService.loadMatches(matchHistory.get());

        SummonerProfile profile =
                SummonerProfile.builder()
                        .id(summoner.getId())
                        .accountId(summoner.getAccountId())
                        .puuid(summoner.getPuuid())
                        .summonerLevel(summoner.getSummonerLevel())
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
