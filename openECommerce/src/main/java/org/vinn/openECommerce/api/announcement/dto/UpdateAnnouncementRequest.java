package org.vinn.openECommerce.api.announcement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAnnouncementRequest {
    private String title;
    private String content;
}