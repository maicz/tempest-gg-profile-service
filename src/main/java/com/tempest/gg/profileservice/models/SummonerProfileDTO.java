package com.tempest.gg.profileservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummonerProfileDTO {
    private String id;
    private String name;
    private String profilePictureUrl;
    private String position;
    List<Ranking> rankings;
}
