package bd2.app.sport.entities;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MatchParticipation {

  @Id
  private Long matchId;

  @ManyToOne
  @JoinColumn(name = "representation_id", nullable = false)
  private Representation representation;

  private Long place;

  private BigDecimal result;

  private BigDecimal score;

  @ManyToOne
  @JoinColumn(name = "unit_id", nullable = false)
  private Unit unit;
}
