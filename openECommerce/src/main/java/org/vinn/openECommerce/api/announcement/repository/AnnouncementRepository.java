package org.vinn.openECommerce.api.announcement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vinn.openECommerce.api.announcement.model.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}
