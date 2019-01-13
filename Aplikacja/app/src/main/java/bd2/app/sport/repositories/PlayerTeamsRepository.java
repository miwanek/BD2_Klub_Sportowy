package bd2.app.sport.repositories;

import bd2.app.sport.entities.PlayerTeams;
import bd2.app.sport.classId.PlayerTeamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerTeamsRepository extends JpaRepository<PlayerTeams, PlayerTeamId> {
}
