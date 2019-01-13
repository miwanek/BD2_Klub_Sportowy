package bd2.app.sport.classId;

import bd2.app.sport.entities.Discipline;
import bd2.app.sport.entities.Player;
import bd2.app.sport.entities.PlayerDiscipline;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class PlayerDisciplineId implements Serializable {

    private Discipline discipline;
    private Player player;
}
