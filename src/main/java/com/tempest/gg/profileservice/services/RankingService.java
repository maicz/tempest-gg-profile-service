package com.tempest.gg.profileservice.services;

import com.tempest.gg.profileservice.clients.RankingClient;
import com.tempest.gg.profileservice.models.Ranking;
import com.tempest.gg.profileservice.models.riot.RiotRanking;
import com.tempest.gg.profileservice.models.riot.RiotSummonerProfile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class RankingService {

    private final RankingClient rankingClient;

    public RankingService(RankingClient rankingClient) {
        this.rankingClient = rankingClient;
    }

    @Async
    public Future<List<Ranking>> getRankings(RiotSummonerProfile summoner) {
        List<RiotRanking> riotRiotRankings = rankingClient.getSummonerRankings(summoner.getId());
        List<Ranking> rankings = new ArrayList<>();
        riotRiotRankings.forEach(riotRank -> rankings.add(Ranking.builder()
                .rank(riotRank.getRank())
                .queueType(riotRank.getQueueType())
                .leaguePoints(riotRank.getLeaguePoints())
                .losses(riotRank.getLosses())
                .wins(riotRank.getWins())
                .tier(riotRank.getTier())
                .winRate(calculateWinRate(riotRank.getWins(), riotRank.getLosses()))
                .build()));
        return new AsyncResult<>(rankings);
    }

    private Integer calculateWinRate(Integer wins, Integer losses) {
        return Float.valueOf(wins.floatValue() / (wins.floatValue() + losses.floatValue()) * 100).intValue();
    }

}
