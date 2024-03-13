package io.github.justthieenj.dotafetchmatch.utils;

import io.github.justthieenj.dotafetchmatch.dataobject.MatchResult;
import io.github.justthieenj.dotafetchmatch.dataobject.TeamData;

public class Converter {
    private static String getLineUp(TeamData data) {
        var no = data.getNo();
        var pickHeroes = data.getPicks();
        var banHeroes = data.getBans();
        var body = "|team%dside=%s".formatted(no, data.getSide());

        var p = "";
        var b = "";
        for (int i = 0; i < 5; i++) {
            p = p.concat("|t%sh%d=%s".formatted(no, i + 1, pickHeroes.get(i)));
        }

        for (int i = 0; i < 7; i++) {
            b = b.concat("|t%sb%d=%s".formatted(no, i + 1, banHeroes.get(i)));
        }
        return String.join("\n", body, p, b);
    }

    private static String convertLengthFormat(String length) {
        var timeUnits = length.split(":");
        if (timeUnits.length > 2) {
            int minute = Integer.parseInt(timeUnits[0]) * 60 + Integer.parseInt(timeUnits[1]);
            return minute + "m" + timeUnits[2] + "s";
        } else {
            return timeUnits[0] + "m" + timeUnits[1] + "s";
        }
    }

    public static String convertToLPFormat(MatchResult result) {
        var teamResult = getLineUp(result.getTeam1Data()) + "\n" + getLineUp(result.getTeam2Data());
        var mapResult = """
                {{Map
                %s
                |length=%s|winner=%s
                }}
                """;
        var length = convertLengthFormat(result.getLength());
        var winner = result.getTeamWinner().equalsIgnoreCase(result.getTeam1Data().getTeamName()) ? 1 : 2;
        return mapResult.formatted(teamResult, length, winner);
    }
}
