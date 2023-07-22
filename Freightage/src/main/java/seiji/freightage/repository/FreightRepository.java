package seiji.freightage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seiji.freightage.model.Freight;

public interface FreightRepository extends JpaRepository<Freight, Long> {
}
