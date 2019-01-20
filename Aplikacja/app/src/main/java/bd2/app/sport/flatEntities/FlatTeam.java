package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.Team;
import lombok.Getter;

@Getter
public class FlatTeam {

    private Long id;
    private String name;
    private Character sex;
    private Long sectionId;
    private Long tierId;

    public FlatTeam(Team team) {
        id = team.getId();
        name = team.getName();
        sex = team.getSex();
        sectionId = team.getSection().getSectionId();
        tierId = team.getTier().getTierId();
    }
}
