package org.vinn.openECommerce.api.product.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vinn.openECommerce.api.product.dto.AddProductRequest;
import org.vinn.openECommerce.api.product.dto.ProductDTO;
import org.vinn.openECommerce.api.product.dto.UpdateProductRequest;
import org.vinn.openECommerce.api.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid AddProductRequest addProductRequest) {
        ProductDTO productDTO = productService.createProduct(addProductRequest);
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody @Valid UpdateProductRequest updateProductRequest) {
        ProductDTO productDTO = productService.updateProduct(updateProductRequest);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/{code}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable String code) {
        ProductDTO productDTO = productService.getProduct(code);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<ProductDTO> productList = productService.getProducts();
        return ResponseEntity.ok(productList);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String code) {
        productService.deleteProduct(code);
        return ResponseEntity.noContent().build();
    }
}
