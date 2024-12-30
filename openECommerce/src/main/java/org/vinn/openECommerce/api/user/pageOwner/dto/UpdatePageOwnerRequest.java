package org.vinn.openECommerce.api.user.pageOwner.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePageOwnerRequest {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Username is mandatory")
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Phone number is mandatory")
    private String phone;

    @NotBlank(message = "NRC is mandatory")
    private String nrc;

    @NotNull(message = "Enabled status is mandatory")
    private boolean enabled;
}