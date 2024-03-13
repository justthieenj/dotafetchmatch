package io.github.justthieenj.dotafetchmatch.dataobject;

import io.github.justthieenj.dotafetchmatch.enums.TeamSide;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TeamData {
    private int no;
    private String teamName;
    private TeamSide side;
    private List<String> picks;
    private List<String> bans;
}
