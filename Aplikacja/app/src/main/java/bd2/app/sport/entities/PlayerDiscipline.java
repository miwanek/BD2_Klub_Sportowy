package bd2.app.sport.entities;


import bd2.app.sport.classId.PlayerDisciplineId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@IdClass(value = PlayerDisciplineId.class)
public class PlayerDiscipline implements Serializable {

  @Id
  @ManyToOne
  @JoinColumn(name = "discipline_id", nullable = false)
  private Discipline discipline;

  @Id
  @ManyToOne
  @JoinColumn(name = "player_representation_id", nullable = false)
  private Player player;
}
