package bd2.app.sport.entities;

import bd2.app.sport.classId.TrainerDisciplinesId;
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
@IdClass(TrainerDisciplinesId.class)
public class TrainerDiscipline implements Serializable {

  @Id
  @ManyToOne
  @JoinColumn(name = "trainer_id")
  private Trainer trainer;

  @Id
  @ManyToOne
  @JoinColumn(name = "discipline_id")
  private Discipline discipline;

}
