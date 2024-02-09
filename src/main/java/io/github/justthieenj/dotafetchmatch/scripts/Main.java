package io.github.justthieenj.dotafetchmatch.scripts;

import io.github.justthieenj.arrakeenselenium.utils.PropertyUtils;

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
        System.out.println(matchResult);
        teardown();
    }

    public static void setup() {
        PropertyUtils.initSystemProperties();
        initDriver();
    }

    public static void teardown() {
        quitDriver();
    }
}