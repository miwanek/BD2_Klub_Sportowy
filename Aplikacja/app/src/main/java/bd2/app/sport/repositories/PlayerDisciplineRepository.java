package bd2.app.sport.repositories;

import bd2.app.sport.classId.PlayerDisciplineId;
import bd2.app.sport.entities.Player;
import bd2.app.sport.entities.PlayerDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface
PlayerDisciplineRepository extends JpaRepository<PlayerDiscipline, PlayerDisciplineId> {

    List<PlayerDiscipline> findById_Player(Player player);
}
