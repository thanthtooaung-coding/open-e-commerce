package org.vinn.openECommerce.api.announcement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.vinn.openECommerce.api.announcement.model.Announcement;

import java.time.Instant;
import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    @Query("SELECT a FROM Announcement a WHERE a.createdAt > :recentDate")
    List<Announcement> findRecentAnnouncements(@Param("recentDate") Instant recentDate);
}
