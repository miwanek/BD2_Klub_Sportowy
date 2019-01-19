package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.Team;
import lombok.Getter;

@Getter
public class FlatTeam {

        private Long id;
        private String name;
        private Long sectionId;
        private Long tierId;

        public FlatTeam(Team team) {
            id = team.getId();
            name = team.getName();
            sectionId = team.getSection().getSectionId();
            tierId = team.getTier().getTierId();
        }
}
