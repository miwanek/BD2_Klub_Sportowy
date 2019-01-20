package bd2.app.sport.classId;

import bd2.app.sport.entities.Player;
import bd2.app.sport.entities.Team;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class PlayerTeamId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "team_representation_id", nullable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "player_representation_id", nullable = false)
    private Player player;
}
