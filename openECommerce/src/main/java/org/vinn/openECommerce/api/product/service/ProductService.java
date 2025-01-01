package org.vinn.openECommerce.api.product.service;

import org.vinn.openECommerce.api.product.dto.AddProductRequest;
import org.vinn.openECommerce.api.product.dto.ProductDTO;
import org.vinn.openECommerce.api.product.dto.UpdateProductRequest;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(AddProductRequest productDto);
    ProductDTO updateProduct(UpdateProductRequest productDto);
    ProductDTO getProduct(String productId);
    List<ProductDTO> getProducts();
    void deleteProduct(String productId);
}
