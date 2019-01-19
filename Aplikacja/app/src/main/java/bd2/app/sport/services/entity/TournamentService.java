package bd2.app.sport.services.entity;

import bd2.app.sport.entities.Game;
import bd2.app.sport.entities.Tournament;
import bd2.app.sport.entities.TournamentDiscipline;
import bd2.app.sport.entities.TournamentParticipation;
import bd2.app.sport.repositories.GameRepository;
import bd2.app.sport.repositories.TournamentDisciplineRepository;
import bd2.app.sport.repositories.TournamentParticipationRepository;
import bd2.app.sport.repositories.TournamentRepository;
import bd2.app.sport.services.AlertFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    private final TournamentDisciplineRepository tournamentDisciplineRepository;
    private final TournamentParticipationRepository tournamentParticipationRepository;
    private final GameRepository gameRepository;


    public List<Tournament> getTournaments(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return tournamentRepository.findAll();
        }
        return null;
    }

    public void deleteTournament(Long id) throws DataIntegrityViolationException {
        tournamentRepository.deleteById(id);
    }

   public List<TournamentDiscipline> getTournamentDisciplines(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return tournamentDisciplineRepository.findAll();
        }
        return null;
    }

    @Transactional
    public void deleteTournamentDiscipline(Long disciplineId, Long tournamentId) throws DataIntegrityViolationException {
        List<Game> blockingGames = gameRepository.findByDiscipline_DisciplineIdAndTournament_TournamentId(disciplineId,tournamentId);
        if(blockingGames.isEmpty()) {
            tournamentDisciplineRepository.deleteByDiscipline_DisciplineIdAndAndTournament_TournamentId(disciplineId, tournamentId);
        }
        else {
            AlertFactory.createAlert("There are still some games in the tournament connected with this discipline");
        }
    }

    public List<TournamentParticipation> getTournamentParticipation(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return tournamentParticipationRepository.findAll();
        }
        return null;
    }
}
