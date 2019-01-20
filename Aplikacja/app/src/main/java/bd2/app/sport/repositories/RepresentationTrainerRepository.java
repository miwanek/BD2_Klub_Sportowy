package bd2.app.sport.repositories;

import bd2.app.sport.entities.Representation;
import bd2.app.sport.entities.RepresentationTrainer;
import bd2.app.sport.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepresentationTrainerRepository extends JpaRepository<RepresentationTrainer, Long> {

    List<RepresentationTrainer> findByRepresentationAndTrainer(Representation representation, Trainer trainer);
}
