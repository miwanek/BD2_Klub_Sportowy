package bd2.app.sport.classId;

import bd2.app.sport.entities.Representation;
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
public class TournamentParticipationId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "representation_id")
    private Representation representation;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
}
