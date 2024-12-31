package org.vinn.openECommerce.api.product.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProductRequest {

    @NotBlank(message = "Product name is mandatory")
    @Size(max = 100, message = "Product name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Product description is mandatory")
    @Size(max = 255, message = "Product description must not exceed 255 characters")
    private String description;

    @NotBlank(message = "Product code is mandatory")
    @Size(min = 45,max = 45, message = "Product code must be 45 characters")
    private String code;

    @NotBlank(message = "Image URL is mandatory")
    @Pattern(
            regexp = "^(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)$",
            message = "Image URL must be a valid URL and end with a supported image extension (e.g., jpg, png, gif)"
    )
    private String imageUrl;

    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Price must have up to 8 digits before the decimal point and 2 digits after")
    private BigDecimal price;

    @Min(value = 0, message = "Stock quantity cannot be negative")
    private int stockQuantity;

    @NotNull(message = "Product category ID is mandatory")
    private Long productCategoryId;
}
