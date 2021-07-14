package com.tempest.gg.profileservice.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    private String matchId;

    private Long gameCreation;
    private Long gameDuration;
    private Date gameStartTimestamp;

    private String gameMode;
    private String gameType;

    private List<Participant> participants;

}
