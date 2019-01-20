package bd2.app.sport.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Section {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long sectionId;

  @Size(min = 1, max = 30)
  private String name;

  @ManyToOne
  @JoinColumn(name = "discipline_id", nullable = false)
  private Discipline discipline;

}
