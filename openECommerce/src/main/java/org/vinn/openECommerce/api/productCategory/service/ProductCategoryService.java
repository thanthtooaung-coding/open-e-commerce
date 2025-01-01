package org.vinn.openECommerce.api.productCategory.service;

import org.vinn.openECommerce.api.productCategory.dto.ProductCategoryDTO;

import java.util.List;

public interface ProductCategoryService {
    ProductCategoryDTO createProductCategory(ProductCategoryDTO productCategoryDTO);
    ProductCategoryDTO updateProductCategory(ProductCategoryDTO productCategoryDTO);
    ProductCategoryDTO getProductCategory(Long id);
    List<ProductCategoryDTO> getProductCategories();
    void deleteProductCategory(Long id);
}
