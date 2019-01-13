package bd2.app.sport.repositories;

import bd2.app.sport.entities.TrainerDisciplines;
import bd2.app.sport.id.TrainerDisciplinesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerDisciplinesRepository extends JpaRepository<TrainerDisciplines, TrainerDisciplinesId> {
}
