package bd2.app.sport.classId;

import bd2.app.sport.entities.Player;
import bd2.app.sport.entities.Team;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;


@Getter
@EqualsAndHashCode
public class PlayerTeamId implements Serializable {

    private Player player;
    private Team team;
}
