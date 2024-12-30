package org.vinn.openECommerce.api.user.pageOwner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vinn.openECommerce.api.user.pageOwner.model.PageOwner;

import java.util.Optional;

public interface PageOwnerRepository extends JpaRepository<PageOwner, Long> {
    Optional<Object> findByUsername(String username);
}
