package com.tempest.gg.profileservice.services;

import com.tempest.gg.profileservice.models.Ranking;
import com.tempest.gg.profileservice.models.SummonerProfileDTO;
import com.tempest.gg.profileservice.models.downstream.RankingRiot;
import com.tempest.gg.profileservice.models.downstream.SummonerProfileRiot;
import com.tempest.gg.profileservice.services.clients.RankingClient;
import com.tempest.gg.profileservice.services.clients.SummonerProfileClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SummonerProfileService {

    private final SummonerProfileClient summonerProfileClient;
    private final RankingClient rankingClient;

    public SummonerProfileService(SummonerProfileClient summonerProfileClient, RankingClient rankingClient) {
        this.summonerProfileClient = summonerProfileClient;
        this.rankingClient = rankingClient;
    }

    public SummonerProfileDTO getSummonerProfile(String summonerName) {
        SummonerProfileRiot summoner = summonerProfileClient.getSummonerByName(summonerName);
        List<RankingRiot> riotRankings = rankingClient.getSummonerRankings(summoner.getId());
        List<Ranking> rankings = new ArrayList<>();
        riotRankings.forEach(riotRank -> rankings.add(Ranking.builder()
                .rank(riotRank.getRank())
                .queueType(riotRank.getQueueType())
                .leaguePoints(riotRank.getLeaguePoints())
                .losses(riotRank.getLosses())
                .wins(riotRank.getWins())
                .tier(riotRank.getTier())
                .winRate(calculateWinRate(riotRank.getWins(), riotRank.getLosses()))
                .build()));

        return SummonerProfileDTO.builder()
                .id(summoner.getId())
                .name(summoner.getName())
                .position("Support")
                .rankings(rankings)
                .profilePictureUrl("http://ddragon.leagueoflegends.com/cdn/11.7.1/img/profileicon/" + summoner.getProfileIconId() + ".png")
                .build();
    }

    private Integer calculateWinRate(Integer wins, Integer losses) {
        return Float.valueOf(wins.floatValue() / (wins.floatValue() + losses.floatValue()) * 100).intValue();
    }
}
