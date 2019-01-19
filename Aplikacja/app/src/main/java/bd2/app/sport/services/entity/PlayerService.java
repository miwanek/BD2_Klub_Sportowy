package bd2.app.sport.services.entity;

import bd2.app.sport.entities.Player;
import bd2.app.sport.entities.PlayerDiscipline;
import bd2.app.sport.entities.PlayerGroup;
import bd2.app.sport.entities.PlayerTeam;
import bd2.app.sport.flatEntities.FlatPlayerDiscipline;
import bd2.app.sport.flatEntities.FlatPlayerTeam;
import bd2.app.sport.repositories.PlayerDisciplineRepository;
import bd2.app.sport.repositories.PlayerGroupRepository;
import bd2.app.sport.repositories.PlayerRepository;
import bd2.app.sport.repositories.PlayerTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerGroupRepository playerGroupRepository;
    private final PlayerDisciplineRepository playerDisciplineRepository;
    private final PlayerTeamRepository playerTeamRepository;

    public List<Player> getPlayers(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return playerRepository.findAll();
        }
        return null;
    }

    public void deletePlayer(Long id) throws DataIntegrityViolationException {
        playerRepository.deleteById(id);
    }

    public List<PlayerGroup> getPlayersGroups(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return playerGroupRepository.findAll();
        }
        return null;
    }

    public void deletePlayerGroup(Long id) throws DataIntegrityViolationException {
        playerGroupRepository.deleteById(id);
    }

    public List<PlayerDiscipline> getPlayerDisciplines(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return playerDisciplineRepository.findAll();
        }
        return null;
    }

    @Transactional
    public void deletePlayerDiscipline(FlatPlayerDiscipline flatPlayerDiscipline) throws DataIntegrityViolationException {
        playerDisciplineRepository.deleteByPlayer_IdAndDiscipline_DisciplineId(flatPlayerDiscipline.getPlayerId(), flatPlayerDiscipline.getDisciplineId());
    }

    public List<PlayerTeam> getPlayerTeams(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return playerTeamRepository.findAll();
        }
        return null;
    }

    @Transactional
    public void deletePlayerTeam(FlatPlayerTeam flatPlayerTeam) throws DataIntegrityViolationException {
        playerTeamRepository.deleteByPlayer_IdAndTeam_Id(flatPlayerTeam.getPlayerId(), flatPlayerTeam.getTeamId());
    }
}
