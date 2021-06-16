package com.tempest.gg.profileservice.clients;

import com.tempest.gg.profileservice.models.riot.RiotRanking;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "summonerRankings", url = "${riot.api.url.v4}")
public interface RankingClient {

    @RequestMapping(method = RequestMethod.GET, value = "league/v4/entries/by-summoner/{summonerId}")
    public List<RiotRanking> getSummonerRankings(@PathVariable(name = "summonerId") String summonerId);

}
