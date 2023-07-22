package seiji.freightage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seiji.freightage.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
