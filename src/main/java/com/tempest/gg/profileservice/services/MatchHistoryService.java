package com.tempest.gg.profileservice.services;

import com.tempest.gg.profileservice.clients.MatchHistoryClient;
import com.tempest.gg.profileservice.models.riot.RiotMatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Service
@Slf4j
public class MatchHistoryService {

    private final MatchHistoryClient matchHistoryClient;

    public MatchHistoryService(MatchHistoryClient matchHistoryClient) {
        this.matchHistoryClient = matchHistoryClient;
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
    public Future<List<RiotMatch>> getCompleteMatchHistory(String playerUUID) {
        List<RiotMatch> result = new ArrayList<>();
        List<String> matchHistory = matchHistoryClient.getMatchHistory(playerUUID);
        matchHistory.forEach(match -> {
            result.add(matchHistoryClient.getMatchById(match));
        });
        return new AsyncResult<>(result);
    }

}
