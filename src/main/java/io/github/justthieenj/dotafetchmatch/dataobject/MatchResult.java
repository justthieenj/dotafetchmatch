package io.github.justthieenj.dotafetchmatch.dataobject;

import io.github.justthieenj.dotafetchmatch.enums.TeamSide;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MatchResult {
    private String matchId; // 7575939183
    private Map<String, List<String>> team1Data; // OG, dire
    private Map<String, List<String>> team2Data; // TEAM SPIRIT, radiant
    private String map;
    private TeamSide sideWinner;
    private String teamWinner;
    private String length;
}
