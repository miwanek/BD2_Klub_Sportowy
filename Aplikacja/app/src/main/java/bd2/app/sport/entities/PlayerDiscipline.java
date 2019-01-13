package bd2.app.sport.entities;


import com.sun.org.glassfish.gmbal.ManagedAttribute;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PlayerDiscipline implements Serializable {

  @Id
  @ManyToOne
  @JoinColumn(name = "discipline_id")
  private Discipline discipline;

  @Id
  @ManyToOne
  @JoinColumn(name = "player_representation_id")
  private Representation playerRepresentation;
}
