package bd2.app.sport.repositories;

import bd2.app.sport.entities.MatchParticipation;
import bd2.app.sport.id.MatchParticipationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchParticipationRepository extends JpaRepository<MatchParticipation, MatchParticipationId> {
}
