package bd2.app.sport.id;

import bd2.app.sport.entities.Player;
import bd2.app.sport.entities.Team;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Getter
@EqualsAndHashCode
public class PlayerTeamId implements Serializable {

    private Player player;
    private Team team;
}
