package bd2.app.sport.entities;

import lombok.*;

import javax.persistence.Column;
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
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Player implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @MapsId
  @JoinColumn(name = "representation_id", nullable = false)
  private Representation representation;

  @Size(min = 1, max = 30)
  private String name;

  @Size(min = 1, max = 30)
  private String surname;

  @NotBlank
  private Character sex;

  private LocalDateTime birthDate;

  @ManyToOne
  @JoinColumn(name = "group_id")
  private PlayerGroup playerGroup;
}
