package bd2.app.sport.classId;

import bd2.app.sport.entities.Discipline;
import bd2.app.sport.entities.Player;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Embeddable
public class PlayerDisciplineId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "discipline_id", referencedColumnName = "discipline_id", nullable = false)
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "player_representation_id", referencedColumnName = "representation_id", nullable = false)
    private Player player;
}
