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
public class MatchParticipation implements Serializable {

  @Id
  @ManyToOne
  @JoinColumn(name = "match_id")
  private Match match;

  @Id
  @ManyToOne
  @JoinColumn(name = "representation_id")
  private Representation representation;

  private Long place;

  private BigDecimal result;

  private BigDecimal score;

  @ManyToOne
  @JoinColumn(name = "unit_id", nullable = false)
  private Unit unit;
}
