package bd2.app.sport.repositories;

import bd2.app.sport.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByName(String name);

    List<Player> findBySurname(String surname);

    List<Player> findByPlayerGroup_GroupId(Long groupId);
}
