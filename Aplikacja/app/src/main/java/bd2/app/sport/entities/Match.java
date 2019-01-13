package bd2.app.sport.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Match {

  @Id
  private Long matchId;

  @Column(nullable = false)
  private LocalDateTime startDate;

  private LocalDateTime endDate;

  @Column(length = 30)
  private String type;

  @Column(length = 30)
  private String referee;

  @Column(nullable = false)
  private String sex;

  @ManyToOne
  @JoinColumn(name = "hall_id", nullable = false)
  private Hall hall;

  @ManyToOne
  @JoinColumn(name = "tournament_id", nullable = false)
  private Tournament tournament;

  @ManyToOne
  @JoinColumn(name = "discipline_id", nullable = false)
  private Discipline discipline;
}
