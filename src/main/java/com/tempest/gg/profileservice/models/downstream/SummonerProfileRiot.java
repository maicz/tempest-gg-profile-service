package com.tempest.gg.profileservice.models.downstream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummonerProfileRiot {

    private String id;
    private String accountId;
    private String puuid;
    private Integer profileIconId;
    private Date revisionDate;
    private Integer summonerLevel;

}