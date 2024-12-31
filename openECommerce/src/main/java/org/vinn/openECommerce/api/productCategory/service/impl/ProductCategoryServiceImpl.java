package org.vinn.openECommerce.api.productCategory.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.vinn.openECommerce.api.productCategory.dto.ProductCategoryDTO;
import org.vinn.openECommerce.api.productCategory.model.ProductCategory;
import org.vinn.openECommerce.api.productCategory.repository.ProductCategoryRepository;
import org.vinn.openECommerce.api.productCategory.service.ProductCategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ModelMapper modelMapper;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository, ModelMapper modelMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductCategoryDTO createProductCategory(ProductCategoryDTO productCategoryDTO) {
        ProductCategory category = modelMapper.map(productCategoryDTO, ProductCategory.class);
        ProductCategory savedCategory = productCategoryRepository.save(category);
        return modelMapper.map(savedCategory, ProductCategoryDTO.class);
    }

    @Override
    public ProductCategoryDTO updateProductCategory(ProductCategoryDTO productCategoryDTO) {
        ProductCategory existingCategory = productCategoryRepository.findById(productCategoryDTO.getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        modelMapper.map(productCategoryDTO, existingCategory);
        ProductCategory updatedCategory = productCategoryRepository.save(existingCategory);

        return modelMapper.map(updatedCategory, ProductCategoryDTO.class);
    }

    @Override
    public ProductCategoryDTO getProductCategory(Long id) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return modelMapper.map(category, ProductCategoryDTO.class);
    }

    @Override
    public List<ProductCategoryDTO> getProductCategories() {
        return productCategoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, ProductCategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProductCategory(Long id) {
        if (!productCategoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        productCategoryRepository.deleteById(id);
    }
}
