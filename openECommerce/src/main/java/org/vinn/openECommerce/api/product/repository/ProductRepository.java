package org.vinn.openECommerce.api.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vinn.openECommerce.api.product.model.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCode(String code);
}
