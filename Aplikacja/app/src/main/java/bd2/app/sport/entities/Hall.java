package bd2.app.sport.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Hall {

  @Id
  private Long hallId;

  @Column(length = 30, nullable = false)
  private String type;

  @Column(nullable = false)
  private Character active;

  private Long maxPlayers;

  private Long maxFans;

  private Long rentalCost;

  @ManyToOne
  @JoinColumn(name = "facility_id")
  private SportFacility sportFacility;
}
