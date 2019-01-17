package bd2.app.sport.repositories;

import bd2.app.sport.classId.MatchParticipationId;
import bd2.app.sport.entities.GameParticipation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameParticipationRepository extends JpaRepository<GameParticipation, MatchParticipationId> {
    void deleteByGame_GameIdAndRepresentation_RepresentationId(Long gameId, String representationId);
}
