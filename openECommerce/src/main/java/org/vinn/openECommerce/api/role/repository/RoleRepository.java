package org.vinn.openECommerce.api.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vinn.openECommerce.api.role.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
