package io.github.justthieenj.dotafetchmatch.scripts;

import io.github.justthieenj.arrakeenselenium.utils.PropertyUtils;
import io.github.justthieenj.dotafetchmatch.dataobject.MatchResult;

import static io.github.justthieenj.arrakeenselenium.core.Arrakeen.*;
import static io.github.justthieenj.dotafetchmatch.page.PageFactory.dotabuff;

public class Main {
    public static void main(String[] args) {
        try {
            setup();
            execute();
        } catch (Exception ignored) {
        } finally {
            teardown();
        }
    }

    private static void execute() {
        var matchId = System.getProperty("matchId", "7575939183");
        var url = "https://www.dotabuff.com/matches/" + matchId;
        open(url);
        sleep(1000);
        var matchResult = dotabuff().getMatchResult();
        System.out.println(matchResult.beautify());
        System.out.println(convertToLPMatch(matchResult, matchResult.getTeam1(), matchResult.getTeam2()));
    }

    private static String convertToLPMatch(MatchResult result, String team1, String team2) {
        var str = "";
        if (team1.equalsIgnoreCase(result.getRadiantTeam())) {
            str = result.convertMapResult("radiant", "dire");
        } else if (team2.equalsIgnoreCase(result.getRadiantTeam())) {
            str = result.convertMapResult("dire", "radiant");
        }
        return str;
    }

    private static void setup() {
        PropertyUtils.initSystemProperties();
        initDriver();
    }

    private static void teardown() {
        quitDriver();
    }
}