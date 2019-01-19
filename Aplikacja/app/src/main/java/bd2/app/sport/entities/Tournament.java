package bd2.app.sport.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Tournament {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long tournamentId;

  @Column(length = 30, nullable = false)
  private String name;

  @Column(length = 30)
  private String type;

  @Column(nullable = false)
  private Character sex;

  @Column(nullable = false)
  private LocalDateTime startDate;

  private LocalDateTime endDate;

}
