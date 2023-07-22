package seiji.freightage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seiji.freightage.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
