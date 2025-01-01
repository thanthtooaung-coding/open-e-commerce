package org.vinn.openECommerce.api.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.vinn.openECommerce.api.product.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCode(String code);

    @Query("SELECT p FROM Product p WHERE p.stockQuantity < :threshold")
    List<Product> findLowStockProducts(@Param("threshold") int threshold);

    @Query("SELECT p.name, SUM(oi.quantity) AS totalSold FROM OrderItem oi JOIN oi.product p GROUP BY p.name ORDER BY totalSold DESC")
    List<Object[]> findTopSellingProducts();
}
