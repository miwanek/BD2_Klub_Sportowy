package bd2.app.sport.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class TournamentParticipation implements Serializable {

  @Id
  @ManyToOne
  @JoinColumn(name = "representation_id")
  private Representation representation;

  @Id
  @ManyToOne
  @JoinColumn(name = "tournament_id")
  private Tournament tournament;

  private Long place;

  private BigDecimal score;
}
