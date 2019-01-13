package bd2.app.sport.repositories;

import bd2.app.sport.entities.TournamentDisciplines;
import bd2.app.sport.classId.TournamentDisciplinesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentDisciplinesRepository extends JpaRepository<TournamentDisciplines, TournamentDisciplinesId> {
}
