package bd2.app.sport.services.entity;

import bd2.app.sport.ConditionNotSatisfiedException;
import bd2.app.sport.entities.Player;
import bd2.app.sport.entities.PlayerDiscipline;
import bd2.app.sport.entities.PlayerGroup;
import bd2.app.sport.entities.PlayerTeam;
import bd2.app.sport.entities.Representation;
import bd2.app.sport.flatEntities.FlatPlayer;
import bd2.app.sport.flatEntities.FlatPlayerDiscipline;
import bd2.app.sport.flatEntities.FlatPlayerTeam;
import bd2.app.sport.repositories.PlayerDisciplineRepository;
import bd2.app.sport.repositories.PlayerGroupRepository;
import bd2.app.sport.repositories.PlayerRepository;
import bd2.app.sport.repositories.PlayerTeamRepository;
import bd2.app.sport.repositories.RepresentationRepository;
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

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerGroupRepository playerGroupRepository;
    private final PlayerDisciplineRepository playerDisciplineRepository;
    private final PlayerTeamRepository playerTeamRepository;
    private final RepresentationRepository representationRepository;

    public List<Player> getPlayers(String selectedColumn, String columnValue) {
        if (selectedColumn == null || columnValue == null) {
            return playerRepository.findAll();
        }
        return null;
    }

    public void deletePlayer(Long id) throws DataIntegrityViolationException {
        playerRepository.deleteById(id);
    }

    public List<PlayerGroup> getPlayersGroups(String selectedColumn, String columnValue) {
        if (selectedColumn == null || columnValue == null) {
            return playerGroupRepository.findAll();
        }
        return null;
    }

    public void deletePlayerGroup(Long id) throws DataIntegrityViolationException {
        playerGroupRepository.deleteById(id);
    }

    public List<PlayerDiscipline> getPlayerDisciplines(String selectedColumn, String columnValue) {
        if (selectedColumn == null || columnValue == null) {
            return playerDisciplineRepository.findAll();
        }
        return null;
    }

    @Transactional
    public void deletePlayerDiscipline(FlatPlayerDiscipline flatPlayerDiscipline) throws DataIntegrityViolationException {
        playerDisciplineRepository.deleteByPlayer_IdAndDiscipline_DisciplineId(flatPlayerDiscipline.getPlayerId(), flatPlayerDiscipline.getDisciplineId());
    }

    public List<PlayerTeam> getPlayerTeams(String selectedColumn, String columnValue) {
        if (selectedColumn == null || columnValue == null) {
            return playerTeamRepository.findAll();
        }
        return null;
    }

    @Transactional
    public void deletePlayerTeam(FlatPlayerTeam flatPlayerTeam) throws DataIntegrityViolationException {
        playerTeamRepository.deleteByPlayer_IdAndTeam_Id(flatPlayerTeam.getPlayerId(), flatPlayerTeam.getTeamId());
    }

    @Transactional
    public void addPlayer(List<String> columnValuesList) {
        Representation representation = new Representation();
        representationRepository.save(representation);

        Player.PlayerBuilder playerBuilder;
        try {
            playerBuilder = validatePlayerData(columnValuesList, representation);
        } catch (StringIndexOutOfBoundsException | ConditionNotSatisfiedException exception) {
            AlertFactory.createAlert("Name, surname and sex must not be null");
            return;
        }
        playerRepository.save(playerBuilder.build());
    }

    @Transactional
    public void editPlayer(List<String> columnValuesList, Object entity) {
        FlatPlayer flatPlayer = (FlatPlayer) entity;

        Player player = playerRepository.findById(flatPlayer.getId()).orElseGet(null);

        Player.PlayerBuilder playerBuilder;
        try {
            playerBuilder = validatePlayerData(columnValuesList, player.getRepresentation());
        } catch (StringIndexOutOfBoundsException | ConditionNotSatisfiedException exception) {
            AlertFactory.createAlert("Name, surname and sex must not be null");
            return;
        }

        Player temporaryPlayer = playerBuilder.build();

        player.setBirthDate(temporaryPlayer.getBirthDate());
        player.setSurname(temporaryPlayer.getSurname());
        player.setName(temporaryPlayer.getName());
        player.setPlayerGroup(temporaryPlayer.getPlayerGroup());
        player.setSex(temporaryPlayer.getSex());
        player.setPlayerGroup(temporaryPlayer.getPlayerGroup());
    }

    public void addPlayerGroup(List<String> columnValuesList) {

    }

    public void addPlayerDiscipline(List<String> columnValuesList) {
    }

    public void addPlayerTeam(List<String> columnValuesList) {
    }

    public void editPlayerGroup(List<String> columnValuesList, Object entity) {
    }

    private Player.PlayerBuilder validatePlayerData(List<String> columnValuesList, Representation representation) throws ConditionNotSatisfiedException, StringIndexOutOfBoundsException {
        Optional<PlayerGroup> group = Optional.empty();

        if (!columnValuesList.get(4).isEmpty()) {
            Long groupId = Long.parseLong(columnValuesList.get(4));
            group = playerGroupRepository.findById(groupId);

            if (!group.isPresent()) {
                AlertFactory.createAlert("No group with given id exists");
                throw new ConditionNotSatisfiedException();
            }
        }

        String name = columnValuesList.get(0);
        String surname = columnValuesList.get(1);
        Character sex = columnValuesList.get(2).charAt(0);

        if (!sex.equals('M') && !sex.equals('K')) {
            AlertFactory.createAlert("Sex can only be male(M) or female(K)");
            throw new ConditionNotSatisfiedException();
        }

        LocalDateTime birthDate = null;

        if (!columnValuesList.get(3).isEmpty()) {
            try {
                birthDate = LocalDateTime.parse(columnValuesList.get(3), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            } catch (DateTimeParseException exception) {
                AlertFactory.createAlert("Provide proper data with correct format: \"yyyy-MM-dd HH:mm\"");
                throw new ConditionNotSatisfiedException();
            }
        }

        Player.PlayerBuilder playerBuilder = Player.builder()
                .representation(representation)
                .name(name)
                .surname(surname)
                .sex(sex)
                .birthDate(birthDate);

        group.ifPresent(playerBuilder::playerGroup);

        return playerBuilder;
    }
}
