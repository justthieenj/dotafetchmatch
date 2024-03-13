package io.github.justthieenj.dotafetchmatch.page;

import io.github.justthieenj.arrakeenselenium.core.ArrakeenElement;
import io.github.justthieenj.arrakeenselenium.utils.StringUtils;
import io.github.justthieenj.dotafetchmatch.dataobject.MatchResult;
import io.github.justthieenj.dotafetchmatch.dataobject.TeamData;
import io.github.justthieenj.dotafetchmatch.enums.LineUpType;
import io.github.justthieenj.dotafetchmatch.enums.TeamSide;

import java.util.List;

import static io.github.justthieenj.arrakeenselenium.core.Arrakeen.find;
import static io.github.justthieenj.arrakeenselenium.core.Arrakeen.finds;
import static io.github.justthieenj.arrakeenselenium.enums.Attribute.className;
import static io.github.justthieenj.arrakeenselenium.enums.Attribute.href;
import static io.github.justthieenj.dotafetchmatch.enums.LineUpType.ban;
import static io.github.justthieenj.dotafetchmatch.enums.LineUpType.pick;
import static io.github.justthieenj.dotafetchmatch.enums.TeamSide.dire;
import static io.github.justthieenj.dotafetchmatch.enums.TeamSide.radiant;

public class Dotabuff {
    private final ArrakeenElement mapNumber = find(".game-link.active>.link");
    private final ArrakeenElement teamWinner = find(".match-result .team-text-full");
    private final ArrakeenElement duration = find(".match-victory-subtitle .duration");
    private final ArrakeenElement matchResult = find(".match-result");

    private String getTeam(TeamSide side) {
        var element = find("div:not(.r-only)>.%s .team-text-full".formatted(side.name()));
        return element.getText();
    }

    private String getTeam(int teamNumber) {
        var elements = finds(".head-to-head .team-text-full");
        return elements.getTexts().get(teamNumber - 1);
    }

    private List<String> getHeroes(TeamSide side, LineUpType type) {
        return finds(".team-results>.%s .%s .image a".formatted(side.name(), type.name()))
                .getAttributeValues(href)
                .stream()
                .map(s -> StringUtils.removeText(s, "https://www.dotabuff.com/heroes/").replace('-', ' '))
                .toList();
    }

    public MatchResult getMatchResult() {
        var m = new MatchResult();
        var matchId = StringUtils.getSubstring(mapNumber.getAttribute(href), "\\d+");
        m.setMatchId(matchId);
        m.setMap(mapNumber.getText());
        var sideWinner = StringUtils.getSubstring(matchResult.getAttribute(className), "(dire|radiant)$");
        m.setSideWinner(TeamSide.valueOf(sideWinner));
        m.setTeamWinner(teamWinner.getText());
        m.setLength(duration.getText());
        var radiantTeam = getTeam(radiant);
        var direTeam = getTeam(dire);

        var team1 = getTeam(1);
        var team2 = getTeam(2);

        if (team1.equals(radiantTeam)) {
            m.setTeam1Data(new TeamData(1, radiantTeam, radiant, getHeroes(radiant, pick), getHeroes(radiant, ban)));
            m.setTeam2Data(new TeamData(2, direTeam, dire, getHeroes(dire, pick), getHeroes(dire, ban)));
        } else {
            m.setTeam1Data(new TeamData(1, direTeam, dire, getHeroes(dire, pick), getHeroes(dire, ban)));
            m.setTeam2Data(new TeamData(2, radiantTeam, radiant, getHeroes(radiant, pick), getHeroes(radiant, ban)));
        }
        return m;
    }

}
