package bd2.app.sport.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
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
  private LocalDate startDate;

  private LocalDate endDate;
}
