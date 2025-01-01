package org.vinn.openECommerce.api.announcement.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.vinn.openECommerce.api.announcement.dto.AnnouncementDTO;
import org.vinn.openECommerce.api.announcement.dto.CreateAnnouncementRequest;
import org.vinn.openECommerce.api.announcement.dto.UpdateAnnouncementRequest;
import org.vinn.openECommerce.api.announcement.model.Announcement;
import org.vinn.openECommerce.api.announcement.repository.AnnouncementRepository;
import org.vinn.openECommerce.api.announcement.service.AnnouncementService;
import org.vinn.openECommerce.api.page.model.Page;
import org.vinn.openECommerce.api.page.repository.PageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final PageRepository pageRepository;
    private final ModelMapper modelMapper;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository, PageRepository pageRepository, ModelMapper modelMapper) {
        this.announcementRepository = announcementRepository;
        this.pageRepository = pageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AnnouncementDTO createAnnouncement(CreateAnnouncementRequest request) {
        Announcement announcement = new Announcement();
        announcement.setTitle(request.getTitle());
        announcement.setContent(request.getContent());
        Page page = pageRepository.findById(request.getPageId())
                .orElseThrow(() -> new IllegalArgumentException("Page not found with id: " + request.getPageId()));
        announcement.setPage(page);
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return mapToDTO(savedAnnouncement);
    }

    @Override
    public AnnouncementDTO getAnnouncementById(Long id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Announcement not found"));
        return mapToDTO(announcement);
    }

    @Override
    public List<AnnouncementDTO> getAllAnnouncements() {
        return announcementRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AnnouncementDTO updateAnnouncement(Long id, UpdateAnnouncementRequest request) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Announcement not found"));

        modelMapper.map(request, announcement);
        Announcement updatedAnnouncement = announcementRepository.save(announcement);
        return mapToDTO(updatedAnnouncement);
    }

    @Override
    public void deleteAnnouncement(Long id) {
        if (!announcementRepository.existsById(id)) {
            throw new IllegalArgumentException("Announcement not found");
        }
        announcementRepository.deleteById(id);
    }

    private AnnouncementDTO mapToDTO(Announcement announcement) {
        return modelMapper.map(announcement, AnnouncementDTO.class);
    }
}
