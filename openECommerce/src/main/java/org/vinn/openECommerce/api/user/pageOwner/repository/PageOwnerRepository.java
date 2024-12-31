package org.vinn.openECommerce.api.user.pageOwner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vinn.openECommerce.api.user.pageOwner.model.PageOwner;

import java.time.Instant;
import java.util.Optional;

public interface PageOwnerRepository extends JpaRepository<PageOwner, Long> {
    Optional<Object> findByUsername(String username);

    @Query("SELECT COUNT(po) FROM PageOwner po WHERE po.createdAt >= :startDate")
    long countByCreatedAtAfter(Instant startDate);
}
