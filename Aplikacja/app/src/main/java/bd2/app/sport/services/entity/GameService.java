package bd2.app.sport.services.entity;

import bd2.app.sport.entities.Game;
import bd2.app.sport.entities.GameParticipation;
import bd2.app.sport.repositories.GameParticipationRepository;
import bd2.app.sport.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final GameParticipationRepository gameParticipationRepository;

    public List<Game> getGames(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return gameRepository.findAll();
        }
        return null;
    }

    public void deleteGame(Long id) throws DataIntegrityViolationException {
        gameRepository.deleteById(id);
    }

    public List<GameParticipation> getGameParticipation(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return gameParticipationRepository.findAll();
        }
        return null;
    }

    @Transactional
    public void deleteGameParticipation(Long gameId, String representationId) throws DataIntegrityViolationException {
        gameParticipationRepository.deleteByGame_GameIdAndRepresentation_RepresentationId(gameId, representationId);
    }

}
