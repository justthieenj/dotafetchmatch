package io.github.justthieenj;

import static io.github.justthieenj.driver.Arrakeen.*;

public class Main {
    public static void main(String[] args) {
        setup();
        var matchId = "7575939183";
        var url = "https://www.dotabuff.com/matches/" + matchId;
        open(url);
        sleep(1000);
        teardown();
    }

    public static void setup() {
        initDriver();
    }

    public static void teardown() {
        quitDriver();
    }
}