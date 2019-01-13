package bd2.app.sport.entities;

import bd2.app.sport.classId.PlayerTeamId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@IdClass(PlayerTeamId.class)
public class PlayerTeam implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "team_representation_id", nullable = false)
    private Team team;

    @Id
    @ManyToOne
    @JoinColumn(name = "player_representation_id", nullable = false)
    private Player player;
}
