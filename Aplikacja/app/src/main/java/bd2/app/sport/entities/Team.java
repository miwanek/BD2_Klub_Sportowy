package bd2.app.sport.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class Team implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @MapsId
  @JoinColumn(name = "representation_id", nullable = false)
  private Representation representation;

  @Size(max = 30)
  @NotBlank
  private String name;

  @ManyToOne
  @JoinColumn(name = "section_id", nullable = false)
  private Section section;

  @ManyToOne
  @JoinColumn(name = "tier_id", nullable = false)
  private Tier tier;

  @NotBlank
  private Character sex;
}
