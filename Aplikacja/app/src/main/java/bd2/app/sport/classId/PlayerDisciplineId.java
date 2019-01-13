package bd2.app.sport.classId;

import bd2.app.sport.entities.Discipline;
import bd2.app.sport.entities.Representation;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class PlayerDisciplineId implements Serializable {
    private Discipline discipline;
    private Representation player;
}
