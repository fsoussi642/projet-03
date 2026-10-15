package fr.portfolio.stock;
import org.springframework.data.jpa.repository.JpaRepository;
/** Abstraction d'accès aux données fournie par Spring Data. */
public interface ProductRepository extends JpaRepository<Product,Long> { boolean existsBySku(String sku); }
