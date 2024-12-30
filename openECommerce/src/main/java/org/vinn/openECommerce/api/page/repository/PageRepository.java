package org.vinn.openECommerce.api.page.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vinn.openECommerce.api.page.model.Page;

import java.util.Optional;

public interface PageRepository extends JpaRepository<Page, Long> {
    Optional<Page> findByName(String name);

    Optional<Page> findByUrl(String url);
}
