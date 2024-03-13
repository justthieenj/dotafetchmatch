package io.github.justthieenj.dotafetchmatch.scripts;

import io.github.justthieenj.arrakeenselenium.utils.Log;
import io.github.justthieenj.arrakeenselenium.utils.PropertyUtils;
import io.github.justthieenj.dotafetchmatch.utils.Converter;

import static io.github.justthieenj.arrakeenselenium.core.Arrakeen.*;
import static io.github.justthieenj.dotafetchmatch.page.PageFactory.dotabuff;

public class Main {
    public static void main(String[] args) {
        try {
            setup();
            execute();
        } catch (Exception e) {
            Log.error(e.getMessage());
        } finally {
            teardown();
        }
    }

    private static void execute() {
        var matchId = System.getProperty("matchId", "7630717190");
        var url = "https://www.dotabuff.com/matches/" + matchId;
        open(url);
        sleep(1000);
        var matchResult = dotabuff().getMatchResult();
        System.out.println(Converter.finalConvert(matchResult));
    }

    private static void setup() {
        PropertyUtils.initSystemProperties();
        initDriver();
    }

    private static void teardown() {
        quitDriver();
    }
}