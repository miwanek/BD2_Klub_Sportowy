package bd2.app.sport.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PlayerTeams implements Serializable {

  @Id
  @ManyToOne(targetEntity = Team.class)
  @JoinColumn(name = "team_representation_id", referencedColumnName = "representation_id")
  private Representation teamRepresentation;

  @Id
  @ManyToOne(targetEntity = Player.class)
  @JoinColumn(name = "player_representation_id", referencedColumnName = "representation_id")
  private Representation playerRepresentation;
}
