package org.vinn.openECommerce.api.announcement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vinn.openECommerce.api.announcement.dto.AnnouncementDTO;
import org.vinn.openECommerce.api.announcement.dto.CreateAnnouncementRequest;
import org.vinn.openECommerce.api.announcement.dto.UpdateAnnouncementRequest;
import org.vinn.openECommerce.api.announcement.service.AnnouncementService;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping
    public ResponseEntity<AnnouncementDTO> createAnnouncement(
            @RequestBody CreateAnnouncementRequest request) {
        AnnouncementDTO createdAnnouncement = announcementService.createAnnouncement(request);
        return ResponseEntity.ok(createdAnnouncement);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementDTO> getAnnouncement(@PathVariable Long id) {
        AnnouncementDTO announcement = announcementService.getAnnouncementById(id);
        return ResponseEntity.ok(announcement);
    }

    @GetMapping
    public ResponseEntity<List<AnnouncementDTO>> getAllAnnouncements() {
        return ResponseEntity.ok(announcementService.getAllAnnouncements());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnouncementDTO> updateAnnouncement(
            @PathVariable Long id,
            @RequestBody UpdateAnnouncementRequest request) {
        AnnouncementDTO updatedAnnouncement = announcementService.updateAnnouncement(id, request);
        return ResponseEntity.ok(updatedAnnouncement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.noContent().build();
    }
}
