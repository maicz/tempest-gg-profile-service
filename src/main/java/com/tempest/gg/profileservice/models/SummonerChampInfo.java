package com.tempest.gg.profileservice.models;

import com.tempest.gg.profileservice.models.riot.ChampStatsEntry;
import lombok.Data;

@Data
public class SummonerChampInfo {

    private String champion;
    private String championIconUrl;

    private Float KDA;
    private Float kills;
    private Float deaths;
    private Float assists;

    private Float winRate;
    private Float cpm;

    private Long wins;
    private Long games;


    public SummonerChampInfo(
            String champion,
            Float KDA,
            Float kills,
            Float deaths,
            Float assists,
            Float winRate,
            Float cpm,
            Long wins) {

        this.champion = champion;
        this.KDA = KDA;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.winRate = winRate;
        this.cpm = cpm;
        this.wins = wins;
        this.games = 1L;
        this.championIconUrl = "";
    }

    public void updateStats(ChampStatsEntry entry) {

        this.games++;
        if (entry.getWin()) this.wins++;
        this.winRate = (float) (this.wins * 1.0 / this.games) * 100;

        this.kills = (this.kills + entry.getKills()) / this.games;
        this.deaths = (this.deaths + entry.getDeaths()) / this.games;
        this.assists = (this.assists + entry.getAssists()) / this.games;
        this.cpm = (this.cpm + entry.getCpm()) / this.games;

        this.KDA = (this.kills + this.assists) / this.deaths;
    }
}
