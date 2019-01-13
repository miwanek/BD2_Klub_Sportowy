package bd2.app.sport.repositories;

import bd2.app.sport.entities.SportFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportFacilityRepository extends JpaRepository<SportFacility, Long> {
}
