package com.tempest.gg.profileservice.controllers;


import com.tempest.gg.profileservice.services.SummonerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/summoner-info")
@RequiredArgsConstructor
public class SummonerInfoController {

    private final SummonerInfoService summonerInfoService;

    @GetMapping
    public String getSummonerInfo(String summonerName) {
        return summonerInfoService.getSummonerInfo(summonerName);
    }
}
