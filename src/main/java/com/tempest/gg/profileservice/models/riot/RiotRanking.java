package com.tempest.gg.profileservice.models.riot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiotRanking {

    private String leagueId;
    private String queueType;
    private String tier;
    private String rank;
    private String summonerId;
    private String summonerName;
    private Integer leaguePoints;
    private Integer wins;
    private Integer losses;
    private Boolean veteran;
    private Boolean inactive;
    private Boolean freshBlood;
    private Boolean hotStreak;

}
