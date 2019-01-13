package bd2.app.sport.id;

import bd2.app.sport.entities.Discipline;
import bd2.app.sport.entities.Trainer;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class TrainerDisciplinesId implements Serializable {
    private Trainer trainer;
    private Discipline discipline;
}
