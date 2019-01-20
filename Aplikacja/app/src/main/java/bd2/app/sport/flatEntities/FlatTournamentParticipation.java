package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.TournamentParticipation;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class FlatTournamentParticipation {

    private Long representationId;
    private Long tournamentId;
    private Long place;
    private BigDecimal score;

    public FlatTournamentParticipation(TournamentParticipation tournamentParticipation) {
        representationId = tournamentParticipation.getId().getRepresentation().getRepresentationId();
        tournamentId = tournamentParticipation.getId().getTournament().getTournamentId();
        place = tournamentParticipation.getPlace();
        score = tournamentParticipation.getScore();
    }
}
