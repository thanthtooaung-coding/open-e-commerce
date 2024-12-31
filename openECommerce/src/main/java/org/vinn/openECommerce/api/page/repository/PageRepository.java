package org.vinn.openECommerce.api.page.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.vinn.openECommerce.api.page.model.Page;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface PageRepository extends JpaRepository<Page, Long> {
    Optional<Page> findByName(String name);

    Optional<Page> findByUrl(String url);

    @Query("SELECT COUNT(p) FROM Page p WHERE p.createdAt >= :startDate AND p.createdAt <= :endDate")
    long countPagesInRange(@Param("startDate") Instant startDate, @Param("endDate") Instant endDate);

    @Query("SELECT p.pageOwner.name, COUNT(p) as pageCount FROM Page p GROUP BY p.pageOwner ORDER BY pageCount DESC")
    List<Object[]> getTopPageOwners();

    @Query("SELECT p.createdAt, COUNT(p) FROM Page p GROUP BY p.createdAt ORDER BY p.createdAt")
    List<Object[]> getPagesOverTime();

    long countByActive(boolean b);
}
