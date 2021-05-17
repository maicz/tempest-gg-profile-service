package com.tempest.gg.profileservice.services.clients;

import com.tempest.gg.profileservice.models.downstream.RankingRiot;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "summonerRankings", url = "https://eun1.api.riotgames.com/lol/")
public interface RankingClient {

    @RequestMapping(method = RequestMethod.GET, value = "league/v4/entries/by-summoner/{summonerId}")
    List<RankingRiot> getSummonerRankings(@PathVariable(name = "summonerId") String summonerId);

}
