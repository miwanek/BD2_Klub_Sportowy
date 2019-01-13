package bd2.app.sport.id;

import bd2.app.sport.entities.Discipline;
import bd2.app.sport.entities.Representation;
import bd2.app.sport.entities.Tournament;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class TournamentDisciplinesId implements Serializable {
    private Tournament tournament;
    private Discipline discipline;
}
