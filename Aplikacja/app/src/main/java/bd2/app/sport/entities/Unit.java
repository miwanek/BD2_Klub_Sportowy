package bd2.app.sport.entities;

import lombok.*;

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
public class Unit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long unitId;

  @Size(min = 1, max = 30)
  private String name;
}
