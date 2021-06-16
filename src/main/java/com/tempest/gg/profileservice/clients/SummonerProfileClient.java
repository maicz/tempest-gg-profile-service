package com.tempest.gg.profileservice.clients;

import com.tempest.gg.profileservice.models.riot.RiotSummonerProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "summonerProfiles", url = "${riot.api.url.v4}")
public interface SummonerProfileClient {

    @RequestMapping(method = RequestMethod.GET, value = "summoner/v4/summoners/by-name/{summonerName}")
    RiotSummonerProfile getSummonerByName(@PathVariable(name = "summonerName") String summonerName);

    @RequestMapping(method = RequestMethod.GET, value = "summoner/v4/summoners/by-puuid/{summonerId}")
    RiotSummonerProfile getSummonerById(@PathVariable(name = "summonerId") String summonerId);
}
