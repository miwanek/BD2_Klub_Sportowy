package bd2.app.sport.repositories;

import bd2.app.sport.classId.PlayerTeamId;
import bd2.app.sport.entities.PlayerTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerTeamRepository extends JpaRepository<PlayerTeam, PlayerTeamId> {

    void deleteById_Player_IdAndId_Team_Id(Long playerId, Long teamId);
}
