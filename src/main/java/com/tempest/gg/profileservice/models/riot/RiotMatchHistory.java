package com.tempest.gg.profileservice.models.riot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiotMatchHistory {

    private List<RiotMatch> matches;
}
