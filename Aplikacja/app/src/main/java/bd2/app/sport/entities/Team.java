package bd2.app.sport.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Team implements Serializable {

  @Id
  @OneToOne
  @JoinColumn(name = "representation_id")
  private Representation representation;

  @Column(length = 30)
  private String name;

  @ManyToOne
  @JoinColumn(name = "section_id", nullable = false)
  private Section section;

  @ManyToOne
  @JoinColumn(name = "level_id", nullable = false)
  private Level level;
}
