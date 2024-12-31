package org.vinn.openECommerce.api.productCategory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vinn.openECommerce.api.productCategory.dto.ProductCategoryDTO;
import org.vinn.openECommerce.api.productCategory.service.ProductCategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PostMapping
    public ResponseEntity<ProductCategoryDTO> create(@RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategoryDTO createdCategory = productCategoryService.createProductCategory(productCategoryDTO);
        return ResponseEntity.ok(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> update(
            @PathVariable Long id,
            @RequestBody ProductCategoryDTO productCategoryDTO) {
        productCategoryDTO.setId(id);
        ProductCategoryDTO updatedCategory = productCategoryService.updateProductCategory(productCategoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(productCategoryService.getProductCategory(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryDTO>> getAll() {
        return ResponseEntity.ok(productCategoryService.getProductCategories());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productCategoryService.deleteProductCategory(id);
        return ResponseEntity.noContent().build();
    }
}
