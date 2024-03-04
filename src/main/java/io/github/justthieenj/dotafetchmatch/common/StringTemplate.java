package io.github.justthieenj.dotafetchmatch.common;

public class StringTemplate {
    public static final String MAP_DETAIL = """
            {{Map
            |team1side=
            |t1h1=|t1h2=|t1h3=|t1h4=|t1h5=
            |t1b1=|t1b2=|t1b3=|t1b4=|t1b5=|t1b6=|t1b7=
            |team2side=
            |t2h1=|t2h2=|t2h3=|t2h4=|t2h5=
            |t2b1=|t2b2=|t2b3=|t2b4=|t2b5=|t2b6=|t2b7=
            |length=|winner=
            }}
            """;

    public static final String EXPECTED_STR = """
            {{Map
            |team1side=radiant
            |t1h1=grimstroke|t1h2=shadow demon|t1h3=phantom assassin|t1h4=omniknight|t1h5=kunkka
            |t1b1=chen|t1b2=timbersaw|t1b3=magnus|t1b4=puck|t1b5=naga siren|t1b6=slark|t1b7=lifestealer
            |team2side=dire
            |t2h1=batrider|t2h2=mars|t2h3=undying|t2h4=leshrac|t2h5=weaver
            |t2b1=centaur warrunner|t2b2=rubick|t2b3=dragon knight|t2b4=primal beast|t2b5=tiny|t2b6=storm spirit|t2b7=pangolier
            |length=95m39s |winner=2
            }}
            """;
}
