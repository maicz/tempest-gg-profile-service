package com.tempest.gg.profileservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummonerProfileDTO {
    private String id;
    private String name;
    private String position;
    private String rank;
    private String division;
    private String profilePictureUrl;
}
