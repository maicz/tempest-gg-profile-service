package com.tempest.gg.profileservice.services;

import com.tempest.gg.profileservice.clients.SummonerProfileClient;
import com.tempest.gg.profileservice.models.Match;
import com.tempest.gg.profileservice.models.Participant;
import com.tempest.gg.profileservice.models.Ranking;
import com.tempest.gg.profileservice.models.SummonerProfile;
import com.tempest.gg.profileservice.models.riot.RiotMatch;
import com.tempest.gg.profileservice.models.riot.RiotSummonerProfile;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class SummonerProfileService {

    private final SummonerProfileClient summonerProfileClient;
    private final RankingService rankingService;
    private final MatchHistoryService matchHistoryService;
    private final Map<String, SummonerProfile> cahce = new HashMap<>();

    @Value("${riot.api.datadragon}")
    private String dataDragonUrl;

    public SummonerProfileService(SummonerProfileClient summonerProfileClient, RankingService rankingService, MatchHistoryService matchHistoryService) {
        this.summonerProfileClient = summonerProfileClient;
        this.rankingService = rankingService;
        this.matchHistoryService = matchHistoryService;
    }

    @SneakyThrows
    public SummonerProfile getSummonerProfile(String summonerName) {

        SummonerProfile cachedProfile = cahce.get(summonerName);

        if (cachedProfile != null) {
            return cachedProfile;
        }

        RiotSummonerProfile summoner = summonerProfileClient.getSummonerByName(summonerName);

        Future<List<Ranking>> rankings = rankingService.getRankings(summoner);
        Future<List<RiotMatch>> matchHistory = matchHistoryService.getCompleteMatchHistory(summoner.getPuuid());

        List<Match> collect = matchHistory.get().stream().map(match -> {

            List<Participant> participantList = match.getInfo().getParticipants().stream().map(participant -> {
                Participant p1 = new Participant();
                BeanUtils.copyProperties(participant, p1);
                return p1;
            }).collect(Collectors.toList());

            Match m1 = new Match();
            m1.setMatchId(match.getMetadata().getMatchId());
            m1.setGameCreation(match.getInfo().getGameCreation());
            m1.setGameDuration(match.getInfo().getGameDuration());
            m1.setGameStartTimestamp(match.getInfo().getGameStartTimestamp());
            m1.setGameMode(match.getInfo().getGameMode());
            m1.setGameType(match.getInfo().getGameType());
            m1.setParticipants(participantList);
            return m1;
        }).collect(Collectors.toList());

        SummonerProfile profile =
                SummonerProfile.builder()
                        .id(summoner.getId())
                        .name(summoner.getName())
                        .position("Support")
                        .rankings(rankings.get())
                        .matches(collect)
                        .profilePictureUrl(dataDragonUrl + "/img/profileicon/" + summoner.getProfileIconId() + ".png")
                        .build();

        cahce.put(profile.getName().toLowerCase(), profile);
        return profile;
    }

}
