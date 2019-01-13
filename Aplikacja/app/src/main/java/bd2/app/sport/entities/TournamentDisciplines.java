package bd2.app.sport.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TournamentDisciplines {

  @Id
  @ManyToOne
  @JoinColumn(name = "discipline_id")
  private Discipline discipline;

  @Id
  @ManyToOne
  @JoinColumn(name = "discipline_id")
  private Tournament tournament;

}
