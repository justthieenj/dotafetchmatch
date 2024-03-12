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

    public String convertTeam1Result(List<String> team1picks, List<String> team1bans) {
        return """
            |t1h1=%s|t1h2=%s|t1h3=%s|t1h4=%s|t1h5=%s
            |t1b1=%s|t1b2=%s|t1b3=%s|t1b4=%s|t1b5=%s|t1b6=%s|t1b7=%s""".formatted(team1picks.get(0), team1picks.get(1), team1picks.get(2), team1picks.get(3), team1picks.get(4),
                team1bans.get(0), team1bans.get(1), team1bans.get(2), team1bans.get(3), team1bans.get(4), team1bans.get(5), team1bans.get(6));
    }

    public String convertTeam2Result(List<String> team2picks, List<String> team2bans) {
        return """
            |t2h1=%s|t2h2=%s|t2h3=%s|t2h4=%s|t2h5=%s
            |t2b1=%s|t2b2=%s|t2b3=%s|t2b4=%s|t2b5=%s|t2b6=%s|t2b7=%s""".formatted(team2picks.get(0), team2picks.get(1), team2picks.get(2), team2picks.get(3), team2picks.get(4),
                team2bans.get(0), team2bans.get(1), team2bans.get(2), team2bans.get(3), team2bans.get(4), team2bans.get(5), team2bans.get(6));
    }

    public String convertMapResult(String team1side, String team2side) {
        String team1result = "";
        String team2result = "";
        if (team1side.equals("radiant")) {
            team1result = convertTeam1Result(radiantPicks, radiantBans);
            team2result = convertTeam2Result(direPicks, direBans);
        } else if (team1side.equals("dire")) {
            team1result = convertTeam1Result(direPicks, direBans);
            team2result = convertTeam2Result(radiantPicks, radiantBans);
        }

        return """
            {{Map
            |team1side=%s
            %s
            |team2side=%s
            %s
            |length=%s|winner=%s
            }}
            """.formatted(team1side, team1result, team2side, team2result,
                convertLengthFormat(length), teamWinner.equalsIgnoreCase(team1) ? 1 : 2);
    }
    private String convertLengthFormat(String length) {
        var split = length.split(":");
        int minute = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
        return minute + "m" + split[2] + "s";
    }
}
