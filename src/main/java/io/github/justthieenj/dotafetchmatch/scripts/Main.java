package io.github.justthieenj.dotafetchmatch.scripts;

import io.github.justthieenj.arrakeenselenium.utils.PropertyUtils;
import io.github.justthieenj.dotafetchmatch.dataobject.MatchResult;

import static io.github.justthieenj.arrakeenselenium.driver.Arrakeen.*;
import static io.github.justthieenj.dotafetchmatch.page.PageFactory.dotabuff;

public class Main {
    public static void main(String[] args) {
        setup();
        var matchId = System.getProperty("matchId", "7575939183");
        var url = "https://www.dotabuff.com/matches/" + matchId;
        open(url);
        sleep(1000);
        var matchResult = dotabuff().getMatchResult();
        System.out.println(matchResult.beautify());
        teardown();
    }

    private static String convertToLPMatch(MatchResult result, String team1, String team2) {
        var str = "";
        if (team1.equals(result.getRadiantTeam())) {

        } else if (team2.equals(result.getRadiantTeam())) {

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