package bd2.app.sport.classId;

import bd2.app.sport.entities.Discipline;
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
