package com.tempest.gg.profileservice.controllers;

import com.tempest.gg.profileservice.models.Match;
import com.tempest.gg.profileservice.services.MatchHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
@RequiredArgsConstructor
public class MatchHistoryController {

    private final MatchHistoryService matchHistoryService;

    @GetMapping("/{matchId}")
    Match getMatchById(@PathVariable String matchId) {
        return matchHistoryService.getMatchById(matchId);
    }

    @GetMapping()
    List<Match> getMatches(@RequestParam List<String> matchIds) {
        return matchHistoryService.getMatches(matchIds);
    }

}
