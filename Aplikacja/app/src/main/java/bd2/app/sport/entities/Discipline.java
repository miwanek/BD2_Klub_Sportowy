package bd2.app.sport.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Discipline {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long disciplineId;

  @Column(length = 30, nullable = false)
  private String name;

}
