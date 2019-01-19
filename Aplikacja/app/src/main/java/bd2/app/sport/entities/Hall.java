package bd2.app.sport.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Hall {

  @Id
  private Long hallId;

  @Size(min = 1, max = 30)
  private String type;

  @Size(min = 1, max = 30)
  private Character active;

  private Long maxPlayers;

  private Long maxFans;

  private Long rentalCost;

  @ManyToOne
  @JoinColumn(name = "facility_id")
  private SportFacility sportFacility;
}
