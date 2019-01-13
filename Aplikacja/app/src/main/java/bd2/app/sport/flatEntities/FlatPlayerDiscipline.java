package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.PlayerDiscipline;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class FlatPlayerDiscipline {

    private Long disciplineId;
    private String playerId;

    public FlatPlayerDiscipline(PlayerDiscipline playerDiscipline) {
        disciplineId = playerDiscipline.getDiscipline().getDisciplineId();
        playerId = playerDiscipline.getPlayer().getId();
    }
}
