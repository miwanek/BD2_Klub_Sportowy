package bd2.app.sport.repositories;

import bd2.app.sport.entities.Representation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentationRepository extends JpaRepository<Representation, String> {
}
