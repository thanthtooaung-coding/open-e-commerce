package org.vinn.openECommerce.api.productCategory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vinn.openECommerce.api.productCategory.model.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
