package bd2.app.sport.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Discipline {

  @Id
  @Column(name = "discipline_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long disciplineId;

  @Size(min = 1, max = 30)
  private String name;

}
