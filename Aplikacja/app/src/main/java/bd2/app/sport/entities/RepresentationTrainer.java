package bd2.app.sport.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RepresentationTrainer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long contractNumber;

  @ManyToOne
  @JoinColumn(name = "trainer_id", nullable = false)
  private Trainer trainer;

  @ManyToOne
  @JoinColumn(name = "representation_id", nullable = false)
  private Representation representation;

  @Column(nullable = false)
  private LocalDateTime startDate;

  private LocalDateTime endDate;
}
