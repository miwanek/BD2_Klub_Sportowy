package bd2.app.sport.entities;

import bd2.app.sport.classId.TournamentDisciplinesId;
import lombok.*;

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
@IdClass(TournamentDisciplinesId.class)
public class TournamentDisciplines implements Serializable {

  @Id
  @ManyToOne
  @JoinColumn(name = "discipline_id")
  private Discipline discipline;

  @Id
  @ManyToOne
  @JoinColumn(name = "tournament_id")
  private Tournament tournament;

}
