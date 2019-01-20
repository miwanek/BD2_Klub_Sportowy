package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.TournamentDiscipline;
import lombok.Getter;

@Getter
public class FlatTournamentDiscipline {

    private Long disciplineId;
    private Long tournamentId;

    public FlatTournamentDiscipline(TournamentDiscipline tournamentDiscipline) {
        disciplineId = tournamentDiscipline.getId().getDiscipline().getDisciplineId();
        tournamentId = tournamentDiscipline.getId().getTournament().getTournamentId();
    }
}
