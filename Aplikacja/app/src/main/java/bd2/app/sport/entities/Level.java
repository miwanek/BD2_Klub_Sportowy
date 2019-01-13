package bd2.app.sport.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Level {

  @Id
  @Column(length = 30)
  private String levelId;

  @Column(length = 30, nullable = false)
  private String name;
}
