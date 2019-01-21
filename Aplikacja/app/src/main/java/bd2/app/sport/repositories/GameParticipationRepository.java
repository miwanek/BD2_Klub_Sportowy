package bd2.app.sport.repositories;

import bd2.app.sport.classId.MatchParticipationId;
import bd2.app.sport.entities.GameParticipation;
import bd2.app.sport.entities.Representation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameParticipationRepository extends JpaRepository<GameParticipation, MatchParticipationId> {
    void deleteById_Game_GameIdAndId_Representation_RepresentationId(Long gameId, Long representationId);

    List<GameParticipation> findById_Representation(Representation representation);

    Optional<GameParticipation> findById_Representation_RepresentationIdAndId_Game_GameId(Long representationId, Long gameId);
}
