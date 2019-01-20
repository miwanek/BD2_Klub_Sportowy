package bd2.app.sport.entities;

import bd2.app.sport.classId.TournamentParticipationId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class TournamentParticipation implements Serializable {

  @EmbeddedId
  private TournamentParticipationId id;

  private Long place;

  private BigDecimal score;
}
