package bd2.app.sport.entities;


import bd2.app.sport.classId.MatchParticipationId;
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
public class GameParticipation implements Serializable {

  @EmbeddedId
  private MatchParticipationId id;

  private Long place;

  private BigDecimal result;

  private BigDecimal score;
}
