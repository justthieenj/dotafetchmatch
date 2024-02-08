package io.github.justthieenj.dataobject;

import lombok.Data;

@Data
public class Match {
    private String map;
    private String score;
    private int winner;
    private String team1;
    private String team1ban;
    private String team1pick;
    private String team2;
    private String team2ban;
    private String team2pick;
}
