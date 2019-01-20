package bd2.app.sport.classId;

import bd2.app.sport.entities.Discipline;
import bd2.app.sport.entities.Tournament;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TournamentDisciplinesId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
}
