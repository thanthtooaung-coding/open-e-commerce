package org.vinn.openECommerce.api.announcement.service;

import org.vinn.openECommerce.api.announcement.dto.AnnouncementDTO;
import org.vinn.openECommerce.api.announcement.dto.CreateAnnouncementRequest;
import org.vinn.openECommerce.api.announcement.dto.UpdateAnnouncementRequest;

import java.util.List;

public interface AnnouncementService {
    AnnouncementDTO createAnnouncement(CreateAnnouncementRequest request);
    AnnouncementDTO getAnnouncementById(Long id);
    List<AnnouncementDTO> getAllAnnouncements();
    AnnouncementDTO updateAnnouncement(Long id, UpdateAnnouncementRequest request);
    void deleteAnnouncement(Long id);
}
