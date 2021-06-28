package com.tempest.gg.profileservice.models.riot;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiotMatchInfo {
    private Long gameCreation;
    private Long gameDuration;
    private Long gameId;
    private String gameMode;
    private String gameName;
    private Date gameStartTimestamp;
    private String gameType;
    private String gameVersion;
    private Long mapId;
    private List<Participant> participants;
    private String platformId;
    private Long queueId;
    private List<Team> teams;

}
