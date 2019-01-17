package bd2.app.sport.classId;

import bd2.app.sport.entities.Game;
import bd2.app.sport.entities.Representation;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class MatchParticipationId implements Serializable {
    private Game game;
    private Representation representation;
}
