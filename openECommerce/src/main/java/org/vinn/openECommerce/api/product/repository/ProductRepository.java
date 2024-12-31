package org.vinn.openECommerce.api.product.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vinn.openECommerce.api.product.model.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCode(@NotBlank(message = "Product code is mandatory") @Size(min = 45,max = 45, message = "Product code must be 45 characters") String code);
}
