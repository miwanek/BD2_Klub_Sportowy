package bd2.app.sport.repositories;

import bd2.app.sport.entities.GameParticipation;
import bd2.app.sport.classId.MatchParticipationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameParticipationRepository extends JpaRepository<GameParticipation, MatchParticipationId> {
}
