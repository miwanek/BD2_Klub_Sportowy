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
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PlayerGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long groupId;

  @Column(nullable = false)
  private Character sex;

  @Size(min = 1, max = 30)
  private String name;

  @ManyToOne
  @JoinColumn(name = "section_id", nullable = false)
  private Section section;

  @ManyToOne
  @JoinColumn(name = "tier_id", nullable = false)
  private Tier tier;
}
