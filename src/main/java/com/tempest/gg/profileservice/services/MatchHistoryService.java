package com.tempest.gg.profileservice.services;

import com.tempest.gg.profileservice.clients.MatchHistoryClient;
import com.tempest.gg.profileservice.models.riot.RiotMatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

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
    public Future<List<RiotMatch>> getMatchHistory(String playerUUID) {
        return new AsyncResult<>(matchHistoryClient.getMatchHistory(playerUUID).getMatches());
    }
}
