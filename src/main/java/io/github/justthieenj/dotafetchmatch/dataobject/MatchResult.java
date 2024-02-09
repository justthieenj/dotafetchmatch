package io.github.justthieenj.dotafetchmatch.dataobject;

import io.github.justthieenj.dotafetchmatch.enums.TeamSide;
import lombok.Data;

import java.util.List;

@Data
public class MatchResult {
    private String matchId;
    private String map;
    private TeamSide sideWinner;
    private String teamWinner;
    private String length;
    private String radiantTeam;
    private List<String> radiantBans;
    private List<String> radiantPicks;
    private String direTeam;
    private List<String> direBans;
    private List<String> direPicks;
}
