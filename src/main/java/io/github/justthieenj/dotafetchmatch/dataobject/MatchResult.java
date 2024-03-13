package io.github.justthieenj.dotafetchmatch.dataobject;

import io.github.justthieenj.dotafetchmatch.enums.TeamSide;
import lombok.Data;

@Data
public class MatchResult {
    private String matchId;
    private TeamData team1Data;
    private TeamData team2Data;
    private String map;
    private TeamSide sideWinner;
    private String teamWinner;
    private String length;
}
