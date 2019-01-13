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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PlayerDiscipline {

  @Id
  @ManyToOne
  @JoinColumn(name = "discipline_id")
  private Long disciplineId;

  @Id
  @ManyToOne
  @JoinColumn(name = "player_representation_id", table = "player")
  private Representation playerRepresentation;
}
