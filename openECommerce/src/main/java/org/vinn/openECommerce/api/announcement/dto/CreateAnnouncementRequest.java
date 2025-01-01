package org.vinn.openECommerce.api.announcement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAnnouncementRequest {
    private String title;
    private String content;
    private Long pageId;
}