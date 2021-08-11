package com.tempest.gg.profileservice.controllers;

import com.tempest.gg.profileservice.models.SummonerProfile;
import com.tempest.gg.profileservice.services.SummonerProfileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController()
@RequestMapping("summoners")
@RequiredArgsConstructor
public class SummonerProfilesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SummonerProfilesController.class);

    private final SummonerProfileService summonerProfileService;

    @GetMapping("/{summonerName}")
    public SummonerProfile getSummonerProfileByName(@PathVariable(name = "summonerName") String summonerName) {
        LOGGER.info("Fetching summoner: {}", summonerName);
        return summonerProfileService.getSummonerProfile(summonerName);
    }

}
