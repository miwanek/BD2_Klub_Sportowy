package bd2.app.sport.services.entity;

import bd2.app.sport.entities.Player;
import bd2.app.sport.entities.PlayerGroup;
import bd2.app.sport.repositories.PlayerGroupRepository;
import bd2.app.sport.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerGroupRepository playerGroupRepository;

    public List<Player> getPlayers(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return playerRepository.findAll();
        }

        return null;
    }

    public void deletePlayer(String id) throws DataIntegrityViolationException {
        playerRepository.deleteById(id);
    }
}
