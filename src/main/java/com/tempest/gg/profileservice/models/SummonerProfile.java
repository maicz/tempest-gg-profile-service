package com.tempest.gg.profileservice.models;

import com.tempest.gg.profileservice.models.riot.RiotMatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummonerProfile {
    private String id;
    private String name;
    private String profilePictureUrl;
    private String position;
    List<Ranking> rankings;
    List<RiotMatch> matches;
}
