package bd2.app.sport.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PlayerGroup {

  @Id
  private Long groupId;

  @Column(nullable = false)
  private Character sex;

  @Column(length = 30)
  private String name;

  @ManyToOne
  @JoinColumn(name = "section_id", nullable = false)
  private Section section;

  @ManyToOne
  @JoinColumn(name = "tier_id", nullable = false)
  private Tier tier;
}
