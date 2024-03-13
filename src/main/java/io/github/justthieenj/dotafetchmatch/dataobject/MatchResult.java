package io.github.justthieenj.dotafetchmatch.dataobject;

import io.github.justthieenj.dotafetchmatch.enums.TeamSide;
import lombok.Data;

import java.util.List;

@Data
public class MatchResult {
    private String matchId;
    private String team1;
    private String team2;
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

    public String beautify() {
        return """
                MatchResult {
                    matchId=%s,
                    map=%s,
                    sideWinner=%s,
                    teamWinner=%s,
                    length=%s,
                    radiantTeam=%s,
                    radiantBans=%s,
                    radiantPicks=%s,
                    direTeam=%s,
                    direBans=%s,
                    direPicks=%s
                }
               """.formatted(matchId, map, sideWinner, teamWinner, length, radiantTeam, radiantBans, radiantPicks, direTeam, direBans, direPicks);
    }

    private String convertTeamResult(List<String> team1picks, List<String> team1bans, List<String> team2picks, List<String> team2bans) {
        StringBuilder team1result = new StringBuilder("|team1side=%s\n");
        StringBuilder team2result = new StringBuilder("|team2side=%s\n");
        for (int i = 0; i < 5; i++) {
            team1result.append("|t1h").append(i + 1).append("=").append(team1picks.get(i));
            team2result.append("|t2h").append(i + 1).append("=").append(team2picks.get(i));
        }
        team1result.append("\n");
        team2result.append("\n");

        for (int i = 0; i < 7; i++) {
            team1result.append("|t1b").append(i + 1).append("=").append(team1bans.get(i));
            team2result.append("|t2b").append(i + 1).append("=").append(team2bans.get(i));
        }
        team1result.append("\n");
        team2result.append("\n");

        return team1result + team2result.toString();
    }

    public String convertMapResult(String team1side, String team2side) {
        String teamResult = "";
        if (team1side.equalsIgnoreCase("radiant")) {
            teamResult = convertTeamResult(radiantPicks, radiantBans, direPicks, direBans);
        } else if (team1side.equalsIgnoreCase("dire")) {
            teamResult = convertTeamResult(direPicks, direBans, radiantPicks, radiantBans);
        }

        String mapResult = """
            {{Map
            """ + teamResult + """
            |length=%s|winner=%s
            }}
            """;

        return mapResult.formatted(team1side, team2side, convertLengthFormat(length), teamWinner.equalsIgnoreCase(team1) ? 1 : 2);
    }
    private String convertLengthFormat(String length) {
        var timeUnits = length.split(":");
        int minute = Integer.parseInt(timeUnits[0]) * 60 + Integer.parseInt(timeUnits[1]);
        return minute + "m" + timeUnits[2] + "s";
    }
}
