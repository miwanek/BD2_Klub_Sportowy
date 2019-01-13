package bd2.app.sport.repositories;

import bd2.app.sport.entities.TrainerDiscipline;
import bd2.app.sport.classId.TrainerDisciplinesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerDisciplineRepository extends JpaRepository<TrainerDiscipline, TrainerDisciplinesId> {
}
