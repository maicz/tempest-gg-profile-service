package com.tempest.gg.profileservice.controllers;

import com.tempest.gg.profileservice.models.downstream.SummonerProfileRiot;
import com.tempest.gg.profileservice.services.SummonerProfileClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class SummonerProfilesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SummonerProfilesController.class);

    private final SummonerProfileClient summonerProfileClient;

    public SummonerProfilesController(SummonerProfileClient summonerProfileClient) {
        this.summonerProfileClient = summonerProfileClient;
    }

    /*@GetMapping("/{summonerId}")
    public String getSummonerProfileById(@PathVariable String summonerId) {
        LOGGER.info("Fetching summoner with id: {}", summonerId);
        return summonerProfileClient.getSummonerById(summonerId);
    }*/

    @GetMapping("/summoners")
    public SummonerProfileRiot getSummonerProfileByName(@RequestParam(name = "summonerName") String summonerName) {
        LOGGER.info("Fetching summoner: {}", summonerName);
        return summonerProfileClient.getSummonerByName(summonerName);
    }

}
