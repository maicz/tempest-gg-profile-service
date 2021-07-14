package com.tempest.gg.profileservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "players")
public class SummonerProfile {

    @Id
    private String id;
    private String name;
    private String profilePictureUrl;
    private String position;
    List<Ranking> rankings;
    List<String> matches;
}
