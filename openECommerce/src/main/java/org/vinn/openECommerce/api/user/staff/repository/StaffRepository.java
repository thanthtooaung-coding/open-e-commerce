package org.vinn.openECommerce.api.user.staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.vinn.openECommerce.api.user.staff.model.Staff;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Object> findByUsername(String username);

    long countByEnabled(boolean enabled);

    @Query("SELECT s FROM Staff s WHERE s.createdAt > :recentDate")
    List<Staff> findRecentStaff(@Param("recentDate") Instant recentDate);
}
