package com.tempest.gg.profileservice.clients;

import com.tempest.gg.profileservice.models.riot.RiotMatchHistory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "matchHistory", url = "${riot.api.url}")
public interface MatchHistoryClient {

    @GetMapping("match/v4/matchlists/by-account/{accountId}")
    public RiotMatchHistory getMatchHistory(@PathVariable("accountId") String accountId);
}
