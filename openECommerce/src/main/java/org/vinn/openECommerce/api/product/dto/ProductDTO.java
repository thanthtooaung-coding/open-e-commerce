package org.vinn.openECommerce.api.product.dto;

import lombok.Getter;
import lombok.Setter;
import org.vinn.openECommerce.api.productCategory.model.ProductCategory;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {

    private String name;

    private String description;

    private String code;

    private String imageUrl;

    private BigDecimal price;

    private int stockQuantity;

    private ProductCategory productCategory;
}
