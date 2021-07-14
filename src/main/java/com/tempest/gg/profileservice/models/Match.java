package com.tempest.gg.profileservice.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "matches")
public class Match {

    @Id
    private String matchId;

    private Long gameCreation;
    private Long gameDuration;
    private Date gameStartTimestamp;

    private String gameMode;
    private String gameType;

    private List<Participant> participants;

}
