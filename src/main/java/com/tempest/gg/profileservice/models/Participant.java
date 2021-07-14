package com.tempest.gg.profileservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participant {

    private String puuid;
    private String summonerName;
    private String summonerId;
    private Integer summonerLevel;

    private String lane;
    private String individualPosition;
    private String championName;

    private Integer kills;
    private Integer deaths;
    private Integer assists;

    private Boolean win;
}
