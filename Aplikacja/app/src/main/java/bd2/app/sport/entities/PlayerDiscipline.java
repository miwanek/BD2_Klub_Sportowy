package bd2.app.sport.entities;


import bd2.app.sport.classId.PlayerDisciplineId;
import lombok.AllArgsConstructor;
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
public class PlayerDiscipline implements Serializable {

  @EmbeddedId
  private PlayerDisciplineId id;
}
