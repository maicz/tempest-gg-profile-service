package com.tempest.gg.profileservice.models.riot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiotMatch {

    private String platformId;
    private Long gameId;
    private Long champion;
    private Long queue;
    private Long season;
    private Date timestamp;
    private String role;
    private String lane;
}
