package org.vinn.openECommerce.api.user.pageOwner.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class PageOwnerDTO {

    private Long id;

    private String name;

    private String username;

    private String email;

    private String address;

    private String phone;

    private String nrc;

    private String imageUrl;

    private boolean enabled;

    private Instant createdAt;

    private Instant updatedAt;
}