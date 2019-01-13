package bd2.app.sport.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
  private String id;

  @OneToOne
  @MapsId
  @JoinColumn(name = "representation_id")
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
  private Group group;
}
