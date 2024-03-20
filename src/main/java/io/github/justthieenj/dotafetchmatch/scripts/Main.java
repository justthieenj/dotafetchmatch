package io.github.justthieenj.dotafetchmatch.scripts;

import io.github.justthieenj.arrakeenselenium.utils.Log;
import io.github.justthieenj.arrakeenselenium.utils.PropertyUtils;
import io.github.justthieenj.arrakeenselenium.utils.ReflectUtils;
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
        var matchId = ReflectUtils.getProperty("matchId", "7645821730");
        var url = "https://www.dotabuff.com/matches/" + matchId;
        open(url);
        sleep(1000);
        var matchResult = dotabuff().getMatchResult();
        System.out.println(Converter.convertToLPFormat(matchResult));
    }

    private static void setup() {
        System.setProperty("browser", "edge");
        System.setProperty("headless", "true");
        PropertyUtils.initSystemProperties();
        initDriver();
    }

    private static void teardown() {
        quitDriver();
    }
}