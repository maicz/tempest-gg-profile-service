package com.tempest.gg.profileservice.services;

import com.tempest.gg.profileservice.models.Match;
import com.tempest.gg.profileservice.models.Participant;
import com.tempest.gg.profileservice.models.SummonerInfo;
import com.tempest.gg.profileservice.models.SummonerProfile;
import com.tempest.gg.profileservice.models.riot.ChampStatsEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SummonerInfoService {


    private final MatchHistoryService matchHistoryService;
    private final SummonerProfileService summonerProfileService;

    public SummonerInfo getSummonerInfo(String summonerName) {
        SummonerProfile summonerProfile = summonerProfileService.getSummonerProfile(summonerName);
        List<Match> matches = matchHistoryService.getMatches(summonerProfile.getMatches());

        SummonerInfo summonerInfo = new SummonerInfo();
        summonerInfo.setSummonerName(summonerName);

        for (Match m : matches) {
            Participant participant = m.getParticipants().stream().filter(part -> part.getSummonerName().equalsIgnoreCase(summonerName)).findFirst().get();

            summonerInfo.getRoles().add(participant.getLane());
            summonerInfo.getStatsEntries().add(new ChampStatsEntry(
                    participant.getChampionName(),
                    participant.getKills(),
                    participant.getDeaths(),
                    participant.getAssists(),
                    (float) ((participant.getKills() + participant.getAssists()) / participant.getDeaths()),
                    participant.getWin(),
                    participant.getVisionScore(),
                    (float) (participant.getTotalMinionsKilled().longValue() / (m.getGameDuration() / 60000))
            ));
        }
        summonerInfo.updateSummonerInfo();
        return summonerInfo;
    }
}
