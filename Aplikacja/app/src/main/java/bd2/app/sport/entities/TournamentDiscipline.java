package bd2.app.sport.entities;

import bd2.app.sport.classId.TournamentDisciplinesId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class TournamentDiscipline implements Serializable {

  @EmbeddedId
  private TournamentDisciplinesId id;
}
