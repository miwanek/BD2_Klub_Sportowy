package bd2.app.sport.services.entity;

import bd2.app.sport.ConditionNotSatisfiedException;
import bd2.app.sport.classId.PlayerDisciplineId;
import bd2.app.sport.classId.PlayerTeamId;
import bd2.app.sport.entities.Discipline;
import bd2.app.sport.entities.Player;
import bd2.app.sport.entities.PlayerDiscipline;
import bd2.app.sport.entities.PlayerGroup;
import bd2.app.sport.entities.PlayerTeam;
import bd2.app.sport.entities.Representation;
import bd2.app.sport.entities.Section;
import bd2.app.sport.entities.Team;
import bd2.app.sport.entities.Tier;
import bd2.app.sport.flatEntities.FlatPlayer;
import bd2.app.sport.flatEntities.FlatPlayerGroup;
import bd2.app.sport.flatEntities.FlatPlayerTeam;
import bd2.app.sport.repositories.DisciplineRepository;
import bd2.app.sport.repositories.PlayerDisciplineRepository;
import bd2.app.sport.repositories.PlayerGroupRepository;
import bd2.app.sport.repositories.PlayerRepository;
import bd2.app.sport.repositories.PlayerTeamRepository;
import bd2.app.sport.repositories.RepresentationRepository;
import bd2.app.sport.repositories.SectionRepository;
import bd2.app.sport.repositories.TeamRepository;
import bd2.app.sport.repositories.TierRepository;
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
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerGroupRepository playerGroupRepository;
    private final PlayerDisciplineRepository playerDisciplineRepository;
    private final PlayerTeamRepository playerTeamRepository;
    private final RepresentationRepository representationRepository;
    private final SectionRepository sectionRepository;
    private final TierRepository tierRepository;
    private final DisciplineRepository disciplineRepository;
    private final TeamRepository teamRepository;

    public List<Player> getPlayers(String selectedColumn, String columnValue) {
        try {
            if (selectedColumn == null || columnValue == null) {
                return playerRepository.findAll();
            }

            if (selectedColumn.equals("name")) {
                return playerRepository.findByName(columnValue);
            }

            if (selectedColumn.equals("surname")) {
                return playerRepository.findBySurname(columnValue);
            }

            if (selectedColumn.equals("groupId")) {
                return playerRepository.findByPlayerGroup_GroupId(Long.parseLong(columnValue));
            }

        } catch (java.lang.NumberFormatException exception) {
            AlertFactory.createAlert("Selected field requires proper number without any letters");
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

    public List<PlayerTeam> getPlayerTeams(String selectedColumn, String columnValue) {
        if (selectedColumn == null || columnValue == null) {
            return playerTeamRepository.findAll();
        }
        return null;
    }

    @Transactional
    public void deletePlayerTeam(FlatPlayerTeam flatPlayerTeam) throws DataIntegrityViolationException {
        playerTeamRepository.deleteById_Player_IdAndId_Team_Id(flatPlayerTeam.getPlayerId(), flatPlayerTeam.getTeamId());
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

    @Transactional
    public void addPlayerGroup(List<String> columnValuesList) {
        PlayerGroup.PlayerGroupBuilder builder;
        try {
            builder = validatePlayerGroupData(columnValuesList);
        } catch (StringIndexOutOfBoundsException | ConditionNotSatisfiedException exception) {
            AlertFactory.createAlert("Sex must not be null");
            return;
        }
        playerGroupRepository.save(builder.build());
    }

    @Transactional
    public void addPlayerDiscipline(List<String> columnValuesList) {
        Optional<Discipline> discipline = Optional.empty();
        Optional<Player> player = Optional.empty();


        if (!columnValuesList.get(0).isEmpty()) {
            Long disciplineId = Long.parseLong(columnValuesList.get(0));
            discipline = disciplineRepository.findById(disciplineId);

            if (!discipline.isPresent()) {
                AlertFactory.createAlert("No discipline with given id exists");
                return;
            }
        }

        if (!columnValuesList.get(1).isEmpty()) {
            Long playerId = Long.parseLong(columnValuesList.get(1));
            player = playerRepository.findById(playerId);

            if (!player.isPresent()) {
                AlertFactory.createAlert("No player with given id exists");
                return;
            }
        }
        PlayerDiscipline playerDiscipline = new PlayerDiscipline();
        playerDiscipline.setId(new PlayerDisciplineId(discipline.get(), player.get()));

        playerDisciplineRepository.save(playerDiscipline);
    }

    @Transactional
    public void addPlayerTeam(List<String> columnValuesList) {
        Optional<Team> team = Optional.empty();
        Optional<Player> player = Optional.empty();

        if (!columnValuesList.get(0).isEmpty()) {
            Long playerId = Long.parseLong(columnValuesList.get(0));
            player = playerRepository.findById(playerId);

            if (!player.isPresent()) {
                AlertFactory.createAlert("No player with given id exists");
                return;
            }
        }
        if (!columnValuesList.get(1).isEmpty()) {
            Long teamId = Long.parseLong(columnValuesList.get(1));
            team = teamRepository.findById(teamId);

            if (!team.isPresent()) {
                AlertFactory.createAlert("No team with given id exists");
                return;
            }
        }


        Player extractedPlayer = player.get();
        Team extractedTeam = team.get();

        Discipline teamDiscipline = team.get().getSection().getDiscipline();
        List<Discipline> list = playerDisciplineRepository.findById_Player(extractedPlayer)
                .stream()
                .map(x -> x.getId().getDiscipline())
                .collect(Collectors.toList());
        if(!list.contains(teamDiscipline)) {
            AlertFactory.createAlert("Player does not train team discipline");
            return;
        }
        PlayerTeam playerTeam = new PlayerTeam(new PlayerTeamId(extractedTeam, extractedPlayer));
        playerTeamRepository.save(playerTeam);
    }

    @Transactional
    public void editPlayerGroup(List<String> columnValuesList, Object entity) {
        FlatPlayerGroup flatplayerGroup = (FlatPlayerGroup) entity;
        PlayerGroup group = playerGroupRepository.findById(flatplayerGroup.getGroupId()).orElse(null);

        PlayerGroup.PlayerGroupBuilder builder;
        try {
            builder = validatePlayerGroupData(columnValuesList);
        } catch (StringIndexOutOfBoundsException | ConditionNotSatisfiedException exception) {
            AlertFactory.createAlert("Sex must not be null");
            return;
        }

        PlayerGroup temporaryGroup = builder.build();

        if (temporaryGroup.getName() != null) {
            group.setName(temporaryGroup.getName());
        }

        group.setSex(temporaryGroup.getSex());
        group.setSection(temporaryGroup.getSection());
        group.setTier(temporaryGroup.getTier());
    }

    private PlayerGroup.PlayerGroupBuilder validatePlayerGroupData(List<String> columnValuesList) throws ConditionNotSatisfiedException, StringIndexOutOfBoundsException {
        Optional<Section> section = Optional.empty();
        Optional<Tier> tier = Optional.empty();

        Character sex = columnValuesList.get(0).charAt(0);
        String name = columnValuesList.get(1);

        if (!sex.equals('M') && !sex.equals('K') && !sex.equals('O')) {
            AlertFactory.createAlert("Sex can only be male(M) or female(K) or both (O)");
            throw new ConditionNotSatisfiedException();
        }

        if (!columnValuesList.get(2).isEmpty()) {
            Long sectionId = Long.parseLong(columnValuesList.get(2));
            section = sectionRepository.findById(sectionId);

            if (!section.isPresent()) {
                AlertFactory.createAlert("No section with given id exists");
                throw new ConditionNotSatisfiedException();
            }
        }

        if (!columnValuesList.get(3).isEmpty()) {
            Long tierId = Long.parseLong(columnValuesList.get(3));
            tier = tierRepository.findById(tierId);
            if (!section.isPresent()) {
                AlertFactory.createAlert("No tier with given id exists");
                throw new ConditionNotSatisfiedException();
            }
        }

        PlayerGroup.PlayerGroupBuilder builder = PlayerGroup.builder()
                .sex(sex)
                .name(name);
        section.ifPresent(builder::section);
        tier.ifPresent(builder::tier);

        return builder;
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

        if (group.isPresent()) {
            Character groupSex = group.get().getSex();
            if (!sex.equals(groupSex) && !groupSex.equals('O')) {
                AlertFactory.createAlert("Player sex and group sex doesn't match");
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
