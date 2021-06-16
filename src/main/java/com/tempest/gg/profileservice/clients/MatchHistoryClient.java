package com.tempest.gg.profileservice.clients;

import com.tempest.gg.profileservice.models.riot.RiotMatchHistory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "matchHistory", url = "${riot.api.url.v5}")
public interface MatchHistoryClient {

    @GetMapping("match/v5/matches/by-puuid/{playerUUID}")
    public RiotMatchHistory getMatchHistory(@PathVariable("playerUUID") String playerUUID);
}
