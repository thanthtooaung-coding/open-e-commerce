package org.vinn.openECommerce.api.user.systemAdmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vinn.openECommerce.api.user.systemAdmin.model.SystemAdmin;

import java.util.Optional;

public interface SystemAdminRepository extends JpaRepository<SystemAdmin, Long> {
    Optional<Object> findByUsername(String username);
}
