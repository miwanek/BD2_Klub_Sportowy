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
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Player implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @MapsId
  @JoinColumn(name = "representation_id", nullable = false)
  private Representation representation;

  @Column(length = 30, nullable = false)
  private String name;

  @Column(length = 30, nullable = false)
  private String surname;

  @Column(nullable = false)
  private Character sex;

  private LocalDateTime birthDate;

  @ManyToOne
  @JoinColumn(name = "group_id")
  private PlayerGroup playerGroup;
}
