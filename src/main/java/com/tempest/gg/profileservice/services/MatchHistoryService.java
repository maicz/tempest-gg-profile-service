package com.tempest.gg.profileservice.services;

import com.tempest.gg.profileservice.clients.MatchHistoryClient;
import com.tempest.gg.profileservice.models.Match;
import com.tempest.gg.profileservice.models.Participant;
import com.tempest.gg.profileservice.models.riot.RiotMatch;
import com.tempest.gg.profileservice.repositories.MatchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchHistoryService {

    private final MatchHistoryClient matchHistoryClient;
    private final MatchRepository matchRepository;

    @Async
    public Future<List<String>> getMatchHistory(String playerUUID) {
        return new AsyncResult<>(matchHistoryClient.getMatchHistory(playerUUID));
    }

    @Async
    public void loadMatches(List<String> matchIds) {
        matchIds.forEach(matchId ->
                {
                    RiotMatch riotMatch = matchHistoryClient.getMatchById(matchId);
                    Match match = mapRiotMatch(riotMatch);
                    if (matchRepository.findById(match.getMatchId()).isEmpty()) {
                        matchRepository.save(match);
                    }
                }
        );
    }

    public List<Match> getMatches(List<String> matchIds) {
        return matchIds.stream().map(this::getMatchById).collect(Collectors.toList());
    }

    public Match getMatchById(String matchId) {
        Optional<Match> matchOptional = matchRepository.findById(matchId);
        if (matchOptional.isEmpty()) {
            Match match = mapRiotMatch(matchHistoryClient.getMatchById(matchId));
            matchRepository.save(match);
            return match;
        } else {
            return matchOptional.get();
        }
    }

    private Match mapRiotMatch(RiotMatch riotMatch) {
        return new Match(
                riotMatch.getMetadata().getMatchId(),
                riotMatch.getInfo().getGameCreation(),
                riotMatch.getInfo().getGameDuration(),
                riotMatch.getInfo().getGameStartTimestamp(),
                riotMatch.getInfo().getGameMode(),
                riotMatch.getInfo().getGameType(),
                riotMatch.getInfo().getParticipants().stream().map(riotParticipant -> {
                    Participant participant = new Participant();
                    BeanUtils.copyProperties(riotParticipant, participant);
                    return participant;
                }).collect(Collectors.toList()));
    }

}
