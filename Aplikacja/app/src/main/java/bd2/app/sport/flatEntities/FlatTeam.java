package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.Team;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class FlatTeam {

        private String id;
        private String name;
        private Long sectionId;
        private String tierId;

        public FlatTeam(Team team) {
            id = team.getId();
            name = team.getName();
            sectionId = team.getSection().getSectionId();
            tierId = team.getTier().getTierId();
        }
}
