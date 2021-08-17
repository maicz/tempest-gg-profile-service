package com.tempest.gg.profileservice.models.riot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChampStatsEntry {

    private String championName;

    private Integer kills;
    private Integer deaths;
    private Integer assists;

    private String KDA;

    private Boolean win;

    private Integer visionScore;

    private Float cpm;

}
