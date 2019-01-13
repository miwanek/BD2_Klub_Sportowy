package bd2.app.sport.repositories;

import bd2.app.sport.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Game, Long> {
}
