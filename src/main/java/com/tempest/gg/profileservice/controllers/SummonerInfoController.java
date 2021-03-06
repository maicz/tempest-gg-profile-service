package com.tempest.gg.profileservice.controllers;


import com.tempest.gg.profileservice.models.SummonerInfo;
import com.tempest.gg.profileservice.services.SummonerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/summoner-info")
@RequiredArgsConstructor
public class SummonerInfoController {

    private final SummonerInfoService summonerInfoService;

    @GetMapping("/{summonerName}")
    public SummonerInfo getSummonerInfo(@PathVariable String summonerName) {
        return summonerInfoService.getSummonerInfo(summonerName);
    }
}
