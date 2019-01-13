package bd2.app.sport.entities;


import bd2.app.sport.id.MatchParticipationId;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@IdClass(MatchParticipationId.class)
public class GameParticipation implements Serializable {

  @Id
  @ManyToOne
  @JoinColumn(name = "game_id")
  private Game game;

  @Id
  @ManyToOne
  @JoinColumn(name = "representation_id")
  private Representation representation;

  private Long place;

  private BigDecimal result;

  private BigDecimal score;
}
