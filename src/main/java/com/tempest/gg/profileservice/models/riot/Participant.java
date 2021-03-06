package com.tempest.gg.profileservice.models.riot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participant {
    private Integer assists;

    private Integer baronKills;
    private Integer bountyLevel;

    private Integer champExperience;
    private Integer champLevel;
    private Integer championId;
    private String championName;
    private Integer championTransform;
    private Integer consumablesPurchased;

    private Integer damageDealtToBuildings;
    private Integer damagedealtToObjectives;
    private Integer damagedealtToTurrets;
    private Integer damageSelfMitigated;
    private Integer deaths;
    private Integer detectorWardsPlaced;
    private Integer doubleKills;
    private Integer dragonKills;

    private Boolean firstBloodAssist;
    private Boolean firstBloodKill;
    private Boolean firstTowerAssist;
    private Boolean firstTowerKill;

    private Boolean gameEndInEarlySurrender;
    private Boolean gameEndInSurrender;
    private Integer goldEarned;
    private Integer goldSpent;

    private String individualPosition;
    private Integer inhibitorKills;
    private Integer inhibitorLost;
    private Integer item0;
    private Integer item1;
    private Integer item2;
    private Integer item3;
    private Integer item4;
    private Integer item5;
    private Integer item6;
    private Integer itemsPurchased;

    private Integer killingSprees;
    private Integer kills;

    private String lane;
    private Integer largestCriticalStrike;
    private Integer largestKillingSpree;
    private Integer largestMultiKill;
    private Integer longestTimeSpentLiving;

    private Integer magicDamageDealt;
    private Integer magicDamageDealtToChampions;
    private Integer magicDamageTaken;

    private Integer neutralMinionsKilled;
    private Integer nexusKills;
    private Integer nexusLost;

    private Integer objectivesStolen;
    private Integer objectivesStolenAssists;

    private Integer participantId;
    private Integer pentaKills;
    private Perks perks;
    private Integer physicalDamageDealt;
    private Integer physicalDamageDealtToChampions;
    private Integer physicalDamageTaken;
    private Integer profileIcon;
    private String puuid;

    private Integer quadraKills;
    private String riotIdName;
    private String riotIdTagline;
    private String role;

    private Integer sightWardsBoughtInGame;
    private Integer spell1Casts;
    private Integer spell2Casts;
    private Integer spell3Casts;
    private Integer spell4Casts;

    private Integer summoner1Casts;
    private Integer summoner1Id;

    private Integer summoner2Casts;
    private Integer summoner2Id;

    private String summonerId;
    private Integer summonerLevel;
    private String summonerName;

    private Boolean teamEarlySurrender;
    private Integer teamId;
    private String teamPosition;

    private Integer timeCCingOthers;
    private Integer timePlayed;

    private Integer totalDamageDealtToChampions;
    private Integer totalDamageShieldedOnTeammates;
    private Integer totalDamageTaken;
    private Integer totalHeal;
    private Integer totalHealsOnTeammates;
    private Integer totalMinionsKilled;
    private Integer totalTimeCCDealt;
    private Integer totalTimeSpentDead;
    private Integer totalUnitsHealed;
    private Integer tripleKills;
    private Integer trueDamageDealt;
    private Integer trueDamageDealtToChampions;
    private Integer trueDamageTaken;
    private Integer turretKills;
    private Integer turretsLost;
    private Integer unrealKills;
    private Integer visionScore;
    private Integer visionWardsBoughtInGame;
    private Integer wardsKilled;
    private Integer wardsPlaced;
    private Boolean win;
}
