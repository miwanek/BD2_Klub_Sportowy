package bd2.app.sport.classId;

import bd2.app.sport.entities.Representation;
import bd2.app.sport.entities.Tournament;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class TournamentParticipationId implements Serializable {
    private Representation representation;
    private Tournament tournament;
}
