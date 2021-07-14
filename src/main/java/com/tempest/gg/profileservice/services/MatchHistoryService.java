package com.tempest.gg.profileservice.services;

import com.tempest.gg.profileservice.clients.MatchHistoryClient;
import com.tempest.gg.profileservice.models.Match;
import com.tempest.gg.profileservice.models.Participant;
import com.tempest.gg.profileservice.models.riot.RiotMatch;
import com.tempest.gg.profileservice.repositories.MatchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MatchHistoryService {

    private final MatchHistoryClient matchHistoryClient;
    private final MatchRepository matchRepository;

    public MatchHistoryService(MatchHistoryClient matchHistoryClient, MatchRepository matchRepository) {
        this.matchHistoryClient = matchHistoryClient;
        this.matchRepository = matchRepository;
    }

    @Async
    public Future<List<String>> getMatchHistory(String playerUUID) {
        return new AsyncResult<>(matchHistoryClient.getMatchHistory(playerUUID));
    }

    @Async
    public Future<RiotMatch> getMatchById(String matchId) {
        return new AsyncResult<>(matchHistoryClient.getMatchById(matchId));
    }

    @Async
    public Future<String> getMatchHistoryByMatchId(String matchId) {
        return new AsyncResult<>(matchHistoryClient.getMatchTimeLineByMatchId(matchId));
    }

    @Async
    public Future<List<Match>> getRecentMatchHistory(String playerUUID) {

        List<String> matchHistory = matchHistoryClient.getMatchHistory(playerUUID);
        List<Match> result = matchHistory.stream().map(matchId -> {
            RiotMatch riotMatch = matchHistoryClient.getMatchById(matchId);

            List<Participant> participantList = riotMatch.getInfo().getParticipants().stream().map(part -> {
                Participant participant = new Participant();
                BeanUtils.copyProperties(part, participant);
                return participant;
            }).collect(Collectors.toList());
            return new Match(
                    riotMatch.getMetadata().getMatchId(),
                    riotMatch.getInfo().getGameCreation(),
                    riotMatch.getInfo().getGameDuration(),
                    riotMatch.getInfo().getGameStartTimestamp(),
                    riotMatch.getInfo().getGameMode(),
                    riotMatch.getInfo().getGameType(),
                    participantList
            );
        }).collect(Collectors.toList());

        return new AsyncResult<>(result);
    }
}
