package bd2.app.sport.entities;

import lombok.*;

import javax.persistence.Column;
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
public class Section {

  @Id
  private Long sectionId;

  @Column(length = 30)
  private String name;

  @ManyToOne
  @JoinColumn(name = "discipline_id", nullable = false)
  private Discipline discipline;

}
