package com.tempest.gg.profileservice.models.riot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiotSummonerProfile {

    private String id;
    private String accountId;
    private String name;
    private String puuid;
    private Integer profileIconId;
    private Date revisionDate;
    private Integer summonerLevel;

    private List<RiotMatch> matchList;

}
