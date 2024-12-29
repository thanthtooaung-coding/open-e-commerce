package org.vinn.eCommerceAdmin.api.systemAdmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vinn.eCommerceAdmin.api.systemAdmin.model.SystemAdmin;

import java.util.Optional;

public interface SystemAdminServiceRepository extends JpaRepository<SystemAdmin, Long> {
    Optional<Object> findByUsername(String username);
}
