package org.vinn.openECommerce.api.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vinn.openECommerce.api.page.model.Page;
import org.vinn.openECommerce.api.page.repository.PageRepository;
import org.vinn.openECommerce.api.product.dto.AddProductRequest;
import org.vinn.openECommerce.api.product.dto.ProductDTO;
import org.vinn.openECommerce.api.product.dto.UpdateProductRequest;
import org.vinn.openECommerce.api.product.model.Product;
import org.vinn.openECommerce.api.product.repository.ProductRepository;
import org.vinn.openECommerce.api.product.service.ProductService;
import org.vinn.openECommerce.api.productCategory.model.ProductCategory;
import org.vinn.openECommerce.api.productCategory.repository.ProductCategoryRepository;
import org.vinn.openECommerce.api.user.staff.model.Staff;
import org.vinn.openECommerce.api.user.staff.repository.StaffRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final PageRepository pageRepository;
    private final StaffRepository staffRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductDTO createProduct(AddProductRequest addProductRequest) {
        ProductCategory category = productCategoryRepository.findById(addProductRequest.getProductCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Product Category not found with ID: " + addProductRequest.getProductCategoryId()));

        Page page = pageRepository.findById(addProductRequest.getPageId())
                .orElseThrow(() -> new IllegalArgumentException("Page not found with ID: " + addProductRequest.getPageId()));

        Staff staff = null;

        if (addProductRequest.getStaffId() != null) {
             staff = staffRepository.findById(addProductRequest.getStaffId())
                    .orElseThrow(() -> new IllegalArgumentException("Staff not found with ID: " + addProductRequest.getStaffId()));
        }

        Product product = modelMapper.map(addProductRequest, Product.class);
        product.setProductCategory(category);
        product.setPage(page);
        product.setStaff(staff);
        product.setCode(generateUniqueProductCode(product.getName()));

        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(UpdateProductRequest updateProductRequest) {
        Product existingProduct = productRepository.findByCode(updateProductRequest.getCode())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with code: " + updateProductRequest.getCode()));

        ProductCategory category = productCategoryRepository.findById(updateProductRequest.getProductCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Product Category not found with ID: " + updateProductRequest.getProductCategoryId()));

        existingProduct.setName(updateProductRequest.getName());
        existingProduct.setDescription(updateProductRequest.getDescription());
        existingProduct.setPrice(updateProductRequest.getPrice());
        existingProduct.setStockQuantity(updateProductRequest.getStockQuantity());
        existingProduct.setProductCategory(category);

        Product updatedProduct = productRepository.save(existingProduct);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO getProduct(String productCode) {
        Product product = productRepository.findByCode(productCode)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with code: " + productCode));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(String productCode) {
        Product product = productRepository.findByCode(productCode)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with code: " + productCode));
        productRepository.delete(product);
    }

    /**
     * Generates a unique product code based on the product name and a random UUID.
     * <p>
     * The product code includes a sanitized version of the product name (up to 10 alphanumeric characters)
     * followed by a random alphanumeric string. This ensures readability, uniqueness, and adherence to
     * the 45-character limit.
     * </p>
     *
     * @param productName the name of the product to be included in the generated code
     * @return a unique 45-character product code
     * @throws IllegalArgumentException if the product name is null or empty
     */
    private String generateUniqueProductCode(String productName) {
        validateProductName(productName);

        String sanitizedProductName = sanitizeProductName(productName);
        String randomSuffix = generateRandomAlphanumeric(35 - sanitizedProductName.length());
        String generatedCode = sanitizedProductName + "-" + randomSuffix;

        while (productRepository.findByCode(generatedCode).isPresent()) {
            randomSuffix = generateRandomAlphanumeric(35 - sanitizedProductName.length());
            generatedCode = sanitizedProductName + "-" + randomSuffix;
        }

        return generatedCode;
    }

    /**
     * Validates the product name to ensure it is not null or empty.
     *
     * @param productName the product name to validate
     * @throws IllegalArgumentException if the product name is null or empty
     */
    private void validateProductName(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name must not be null or empty");
        }
    }

    /**
     * Sanitizes the product name by converting it to uppercase, removing non-alphanumeric characters,
     * and truncating it to a maximum of 10 characters.
     *
     * @param productName the product name to sanitize
     * @return a sanitized version of the product name
     */
    private String sanitizeProductName(String productName) {
        return productName.toUpperCase()
                .replaceAll("[^A-Z0-9]", "")
                .substring(0, Math.min(productName.length(), 10));
    }

    /**
     * Generates a random alphanumeric string of the specified length.
     *
     * @param length the length of the random string to generate
     * @return a random alphanumeric string
     * @throws IllegalArgumentException if the length is less than or equal to 0
     */
    private String generateRandomAlphanumeric(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Random string length must be greater than 0");
        }

        StringBuilder randomString = new StringBuilder();
        while (randomString.length() < length) {
            randomString.append(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        }
        return randomString.substring(0, length);
    }

}
