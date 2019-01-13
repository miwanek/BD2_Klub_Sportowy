package bd2.app.sport.repositories;

import bd2.app.sport.entities.TournamentParticipation;
import bd2.app.sport.id.TournamentParticipationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentParticipationRepository extends JpaRepository<TournamentParticipation, TournamentParticipationId> {
}
