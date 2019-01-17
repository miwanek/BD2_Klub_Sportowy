package bd2.app.sport.repositories;

import bd2.app.sport.entities.RepresentationTrainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentationTrainerRepository extends JpaRepository<RepresentationTrainer, Long> {
}
