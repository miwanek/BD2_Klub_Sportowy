package bd2.app.sport.repositories;

import bd2.app.sport.entities.Tier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierRepository extends JpaRepository<Tier, Long> {
}
