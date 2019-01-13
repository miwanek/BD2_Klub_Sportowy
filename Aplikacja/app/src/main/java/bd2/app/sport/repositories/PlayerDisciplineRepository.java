package bd2.app.sport.repositories;

import bd2.app.sport.entities.PlayerDiscipline;
import bd2.app.sport.id.PlayerDisciplineId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerDisciplineRepository extends JpaRepository<PlayerDiscipline, PlayerDisciplineId> {

}
