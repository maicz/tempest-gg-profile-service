package com.tempest.gg.profileservice.models.riot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiotMatch {
    private RiotMatchMetadata metadata;
    private RiotMatchInfo info;
}
