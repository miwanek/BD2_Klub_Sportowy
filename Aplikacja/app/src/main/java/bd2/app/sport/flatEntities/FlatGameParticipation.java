package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.GameParticipation;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class FlatGameParticipation {
    private Long gameId;
    private Long representationId;
    private Long place;
    private BigDecimal result;
    private BigDecimal score;

    public FlatGameParticipation(GameParticipation gameParticipation) {
        gameId = gameParticipation.getId().getGame().getGameId();
        representationId = gameParticipation.getId().getRepresentation().getRepresentationId();
        place = gameParticipation.getPlace();
        result = gameParticipation.getResult();
        score = gameParticipation.getScore();
    }
}
