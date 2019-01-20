package bd2.app.sport.repositories;

import bd2.app.sport.entities.Game;
import bd2.app.sport.entities.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByDiscipline_DisciplineIdAndTournament_TournamentId(Long disciplineId, Long tournamentId);

    List<Game> findByHall(Hall hall);
}
