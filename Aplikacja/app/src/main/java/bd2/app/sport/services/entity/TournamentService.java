package bd2.app.sport.services.entity;

import bd2.app.sport.ConditionNotSatisfiedException;
import bd2.app.sport.classId.TournamentDisciplinesId;
import bd2.app.sport.classId.TournamentParticipationId;
import bd2.app.sport.entities.Discipline;
import bd2.app.sport.entities.Game;
import bd2.app.sport.entities.Representation;
import bd2.app.sport.entities.Tournament;
import bd2.app.sport.entities.TournamentDiscipline;
import bd2.app.sport.entities.TournamentParticipation;
import bd2.app.sport.flatEntities.FlatTournamentParticipation;
import bd2.app.sport.repositories.DisciplineRepository;
import bd2.app.sport.repositories.GameRepository;
import bd2.app.sport.repositories.RepresentationRepository;
import bd2.app.sport.repositories.TournamentDisciplineRepository;
import bd2.app.sport.repositories.TournamentParticipationRepository;
import bd2.app.sport.repositories.TournamentRepository;
import bd2.app.sport.services.AlertFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    private final TournamentDisciplineRepository tournamentDisciplineRepository;
    private final TournamentParticipationRepository tournamentParticipationRepository;
    private final RepresentationRepository representationRepository;
    private final GameRepository gameRepository;
    private final DisciplineRepository disciplineRepository;

    public List<Tournament> getTournaments(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return tournamentRepository.findAll();
        }
        return null;
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
            tournamentDisciplineRepository.deleteById_Discipline_DisciplineIdAndId_Tournament_TournamentId(disciplineId, tournamentId);
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

    public void addTournamentParticipation(List<String> columnValuesList) {
        TournamentParticipation.TournamentParticipationBuilder builder;
        try {
            builder = validateTournamentParticipationData(columnValuesList);
        } catch (ConditionNotSatisfiedException exception) {
            return;
        } catch (Exception exception ) {
            AlertFactory.createAlert("Something went wrong");
            return;
        }
        tournamentParticipationRepository.save(builder.build());
    }

    public void editTournamentParticipation(List<String> columnValuesList, Object entity) {
        FlatTournamentParticipation flatTournamentParticipation = (FlatTournamentParticipation) entity;

        TournamentParticipation.TournamentParticipationBuilder builder;
        try {
            builder = validateTournamentParticipationData(columnValuesList);
        } catch (ConditionNotSatisfiedException exception) {
            return;
        }
        catch (Exception exception ) {
            AlertFactory.createAlert("Something went wrong");
            return;
        }

        TournamentParticipation temporary = builder.build();
        Long newRepresentationId = temporary.getId().getRepresentation().getRepresentationId();
        Long newTournamentId = temporary.getId().getTournament().getTournamentId();
        Long oldRepresentationId = flatTournamentParticipation.getRepresentationId();
        Long oldTournamentId = flatTournamentParticipation.getTournamentId();

        if(!(newRepresentationId.equals(oldRepresentationId))
                || !(newTournamentId.equals(oldTournamentId))) {
            AlertFactory.createAlert("You can only modify place and score");
            return;
        }

        tournamentParticipationRepository.save(temporary);
    }

    private TournamentParticipation.TournamentParticipationBuilder validateTournamentParticipationData(List<String> columnValuesList) throws StringIndexOutOfBoundsException, ConditionNotSatisfiedException {
        Optional<Representation> representation = Optional.empty();
        Optional<Tournament> tournament = Optional.empty();
        Long place = null;
        BigDecimal score= null;

        if (!columnValuesList.get(0).isEmpty()) {
            Long representationId = Long.parseLong(columnValuesList.get(0));
            representation = representationRepository.findById(representationId);

            if (!representation.isPresent()) {
                AlertFactory.createAlert("No representation with given id exists");
                throw new ConditionNotSatisfiedException();
            }
        }

        if (!columnValuesList.get(1).isEmpty()) {
            Long tournamentId = Long.parseLong(columnValuesList.get(1));
            tournament = tournamentRepository.findById(tournamentId);

            if (!tournament.isPresent()) {
                AlertFactory.createAlert("No tournament with given id exists");
                throw new ConditionNotSatisfiedException();
            }
        }

        if (!columnValuesList.get(2).isEmpty()) {
            place = Long.parseLong(columnValuesList.get(2));

            if (place < 1) {
                AlertFactory.createAlert("Place has to be greater or equal 1");
                throw new ConditionNotSatisfiedException();
            }
        }
        if (!columnValuesList.get(3).isEmpty()) {
            score = new BigDecimal(columnValuesList.get(3));

            if (score.intValue() < 0) {
                AlertFactory.createAlert("Score can not be less than 0");
                throw new ConditionNotSatisfiedException();
            }
        }
        TournamentParticipationId id = new TournamentParticipationId(representation.get(), tournament.get());

        TournamentParticipation.TournamentParticipationBuilder builder = TournamentParticipation.builder()
                .id(id)
                .place(place)
                .score(score);

        return builder;
    }

    public void addTournamentDiscipline(List<String> columnValuesList) {

        Optional<Discipline> discipline = Optional.empty();
        Optional<Tournament> tournament = Optional.empty();


        if (!columnValuesList.get(0).isEmpty()) {
            Long disciplineId = Long.parseLong(columnValuesList.get(0));
            discipline = disciplineRepository.findById(disciplineId);

            if (!discipline.isPresent()) {
                AlertFactory.createAlert("No discipline with given id exists");
                return;
            }
        }

        if (!columnValuesList.get(1).isEmpty()) {
            Long tournamentId = Long.parseLong(columnValuesList.get(1));
            tournament = tournamentRepository.findById(tournamentId);

            if (!tournament.isPresent()) {
                AlertFactory.createAlert("No tournament with given id exists");
                return;
            }
        }

        TournamentDisciplinesId id = new TournamentDisciplinesId(discipline.get(), tournament.get());

        TournamentDiscipline.TournamentDisciplineBuilder builder = TournamentDiscipline.builder()
                .id(id);

        tournamentDisciplineRepository.save(builder.build());
    }
}
