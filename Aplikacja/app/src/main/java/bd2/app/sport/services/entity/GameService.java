package bd2.app.sport.services.entity;

import bd2.app.sport.ConditionNotSatisfiedException;
import bd2.app.sport.entities.Discipline;
import bd2.app.sport.entities.Game;
import bd2.app.sport.entities.GameParticipation;
import bd2.app.sport.entities.Hall;
import bd2.app.sport.entities.Tournament;
import bd2.app.sport.entities.Unit;
import bd2.app.sport.repositories.DisciplineRepository;
import bd2.app.sport.repositories.GameParticipationRepository;
import bd2.app.sport.repositories.GameRepository;
import bd2.app.sport.repositories.HallRepository;
import bd2.app.sport.repositories.TournamentDisciplineRepository;
import bd2.app.sport.repositories.TournamentRepository;
import bd2.app.sport.repositories.UnitRepository;
import bd2.app.sport.services.AlertFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final GameParticipationRepository gameParticipationRepository;
    private final HallRepository hallRepository;
    private final DisciplineRepository disciplineRepository;
    private final TournamentRepository tournamentRepository;
    private final UnitRepository unitRepository;
    private final TournamentDisciplineRepository tournamentDisciplineRepository;


    public List<Game> getGames(String selectedColumn, String columnValue) {
        if (selectedColumn == null || columnValue == null) {
            return gameRepository.findAll();
        }
        return null;
    }

    public void deleteGame(Long id) throws DataIntegrityViolationException {
        gameRepository.deleteById(id);
    }

    public List<GameParticipation> getGameParticipation(String selectedColumn, String columnValue) {
        if (selectedColumn == null || columnValue == null) {
            return gameParticipationRepository.findAll();
        }
        return null;
    }

    @Transactional
    public void deleteGameParticipation(Long gameId, Long representationId) throws DataIntegrityViolationException {
        gameParticipationRepository.deleteByGame_GameIdAndRepresentation_RepresentationId(gameId, representationId);
    }

    @Transactional
    public void addGame(List<String> columnValuesList) {
        Game.GameBuilder builder;
        try {
            builder = validateGameData(columnValuesList);
        } catch (StringIndexOutOfBoundsException | ConditionNotSatisfiedException exception) {
            return;
        }
        gameRepository.save(builder.build());
    }

    public void addGameParticipation(List<String> columnValuesList) {
    }

    public void editGameParticipation(List<String> columnValuesList, Object entity) {
    }

    private Game.GameBuilder validateGameData(List<String> columnValuesList) throws ConditionNotSatisfiedException, StringIndexOutOfBoundsException {
        LocalDateTime startDate;
        LocalDateTime endDate = null;
        String type = null;
        String referee = null;

        if (!columnValuesList.get(0).isEmpty()) {
            try {
                startDate = LocalDateTime.parse(columnValuesList.get(0), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            } catch (DateTimeParseException exception) {
                AlertFactory.createAlert("Provide proper start date with correct format: \"yyyy-MM-dd HH:mm:ss\"");
                throw new ConditionNotSatisfiedException();
            }
        } else {
            AlertFactory.createAlert("StartDate must not be null");
            throw new ConditionNotSatisfiedException();
        }

        if (!columnValuesList.get(1).isEmpty()) {
            try {
                endDate = LocalDateTime.parse(columnValuesList.get(1), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            } catch (DateTimeParseException exception) {
                AlertFactory.createAlert("Provide proper start date with correct format: \"yyyy-MM-dd HH:mm:ss\"");
                throw new ConditionNotSatisfiedException();
            }
        }

        if (!columnValuesList.get(2).isEmpty()) {
            type = columnValuesList.get(2);
        }

        if (!columnValuesList.get(3).isEmpty()) {
            referee = columnValuesList.get(3);
        }

        Character sex = columnValuesList.get(4).charAt(0);

        if (!sex.equals('M') && !sex.equals('K') && !sex.equals('O')) {
            AlertFactory.createAlert("Sex can only be male(M), female(K) or both(O)");
            throw new ConditionNotSatisfiedException();
        }

        Long hallId;
        Long tournamentId;
        Long disciplineId;
        Long unitId;

        Optional<Hall> hall;
        Optional<Tournament> tournament = Optional.empty();
        Optional<Discipline> discipline;
        Optional<Unit> unit = Optional.empty();

        if (!columnValuesList.get(5).isEmpty()) {
            hallId = Long.parseLong(columnValuesList.get(5));
            hall = hallRepository.findById(hallId);

            if (!hall.isPresent()) {
                AlertFactory.createAlert("No hall with given id exists");
                throw new ConditionNotSatisfiedException();
            }
        } else {
            AlertFactory.createAlert("Hall is mandatory for game");
            throw new ConditionNotSatisfiedException();
        }

        if (!columnValuesList.get(6).isEmpty()) {
            tournamentId = Long.parseLong(columnValuesList.get(6));
            tournament = tournamentRepository.findById(tournamentId);

            if (!tournament.isPresent()) {
                AlertFactory.createAlert("No tournament with given id exists");
                throw new ConditionNotSatisfiedException();
            }
        }

        if (!columnValuesList.get(7).isEmpty()) {
            disciplineId = Long.parseLong(columnValuesList.get(7));
            discipline = disciplineRepository.findById(disciplineId);

            if (!discipline.isPresent()) {
                AlertFactory.createAlert("No discipline with given id exists");
                throw new ConditionNotSatisfiedException();
            }
        } else {
            AlertFactory.createAlert("Discipline is mandatory for game");
            throw new ConditionNotSatisfiedException();
        }

        if (!columnValuesList.get(8).isEmpty()) {
            unitId = Long.parseLong(columnValuesList.get(8));
            unit = unitRepository.findById(unitId);

            if (!unit.isPresent()) {
                AlertFactory.createAlert("No group with given id exists");
                throw new ConditionNotSatisfiedException();
            }
        }

        List<Game> list = gameRepository.findByHall(hall.get());

        for (Game e : list) {
            if (endDate != null) {
                if (e.getStartDate().isAfter(startDate) && e.getEndDate().isBefore(endDate)) {
                    AlertFactory.createAlert("Hall is already reserved for this time");
                    throw new ConditionNotSatisfiedException();
                }
            }
        }

        if (tournament.isPresent()) {
            if (tournament.get().getStartDate().isAfter(startDate)) {
                AlertFactory.createAlert("Match can not start before start of tournament");
                throw new ConditionNotSatisfiedException();
            }

            List<Discipline> disciplineList = tournamentDisciplineRepository.findById_Tournament(tournament.get())
                    .stream()
                    .map(x -> x.getId().getDiscipline())
                    .collect(Collectors.toList());

            if(!disciplineList.contains(discipline.get())) {
                AlertFactory.createAlert("Game discipline does not match tournament discipline");
                throw new ConditionNotSatisfiedException();
            }

            if(!tournament.get().getSex().equals('O')) {
                if(!tournament.get().getSex().equals(sex)) {
                    AlertFactory.createAlert("Game sex does not match tournament sex");
                    throw new ConditionNotSatisfiedException();
                }
            }
        }

        if (endDate != null) {
            if (startDate.isAfter(endDate)) {
                AlertFactory.createAlert("Match can not start after its end");
                throw new ConditionNotSatisfiedException();
            }
        }

        Game.GameBuilder builder = Game.builder()
                .startDate(startDate)
                .endDate(endDate)
                .type(type)
                .referee(referee)
                .sex(sex);

        hall.ifPresent(builder::hall);
        tournament.ifPresent(builder::tournament);
        discipline.ifPresent(builder::discipline);
        unit.ifPresent(builder::unit);

        return builder;
    }
}
