package com.tempest.gg.profileservice.services.clients;

import com.tempest.gg.profileservice.models.downstream.SummonerProfileRiot;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "summonerProfiles", url = "https://eun1.api.riotgames.com/lol/")
public interface SummonerProfileClient {

    @RequestMapping(method = RequestMethod.GET, value = "summoner/v4/summoners/by-name/{summonerName}")
    SummonerProfileRiot getSummonerByName(@PathVariable(name = "summonerName") String summonerName);

    @RequestMapping(method = RequestMethod.GET, value = "summoner/v4/summoners/by-puuid/{summonerId}")
    SummonerProfileRiot getSummonerById(@PathVariable(name = "summonerId") String summonerId);
}
