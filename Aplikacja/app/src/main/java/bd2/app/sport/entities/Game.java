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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long gameId;

  @Column(nullable = false)
  private LocalDateTime startDate;

  private LocalDateTime endDate;

  @Size(min = 1, max = 30)
  private String type;

  @Size(min = 1, max = 30)
  private String referee;

  private Character sex;

  @ManyToOne
  @JoinColumn(name = "hall_id", nullable = false)
  private Hall hall;

  @ManyToOne
  @JoinColumn(name = "tournament_id")
  private Tournament tournament;

  @ManyToOne
  @JoinColumn(name = "discipline_id", nullable = false)
  private Discipline discipline;

  @ManyToOne
  @JoinColumn(name = "unit_id")
  private Unit unit;
}
