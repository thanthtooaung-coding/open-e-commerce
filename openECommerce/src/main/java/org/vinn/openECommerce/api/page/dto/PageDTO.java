package org.vinn.openECommerce.api.page.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class PageDTO {

    private Long id;

    private String name;

    private String shortName;

    private String url;

    private String imageUrl;

    private Instant createdAt;

    private Instant updatedAt;
}
