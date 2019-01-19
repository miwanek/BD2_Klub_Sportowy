package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.PlayerDiscipline;
import lombok.Getter;

@Getter
public class FlatPlayerDiscipline {

    private Long disciplineId;
    private Long playerId;

    public FlatPlayerDiscipline(PlayerDiscipline playerDiscipline) {
        disciplineId = playerDiscipline.getDiscipline().getDisciplineId();
        playerId = playerDiscipline.getPlayer().getId();
    }
}
