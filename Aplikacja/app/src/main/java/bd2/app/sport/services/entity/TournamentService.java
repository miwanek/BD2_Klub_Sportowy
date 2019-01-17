package bd2.app.sport.services.entity;

import bd2.app.sport.entities.Tournament;
import bd2.app.sport.repositories.TournamentDisciplineRepository;
import bd2.app.sport.repositories.TournamentParticipationRepository;
import bd2.app.sport.repositories.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    private final TournamentDisciplineRepository tournamentDisciplineRepository;
    private final TournamentParticipationRepository tournamentParticipationRepository;


    public List<Tournament> getTournaments(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return tournamentRepository.findAll();
        }
        return null;
    }

    public void deleteTournament(Long id) throws DataIntegrityViolationException {
        tournamentRepository.deleteById(id);
    }
///TODO
/*    public List<TournamentDiscipline> getTournamentDisciplines(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return tournamentDisciplineRepository.findAll();
        }
        return null;
    }

    public void deleteTournamentDiscipline(Long id) throws DataIntegrityViolationException {
        tournamentDisciplineRepository.deleteById(id);
    }

    public List<TournamentParticipation> getPlayerDisciplines(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return tournamentParticipationRepository.findAll();
        }
        return null;
    }

    public void deleteTournamentParticipation(Long id) throws DataIntegrityViolationException {
        tournamentParticipationRepository.deleteById(id);
    }*/
}
