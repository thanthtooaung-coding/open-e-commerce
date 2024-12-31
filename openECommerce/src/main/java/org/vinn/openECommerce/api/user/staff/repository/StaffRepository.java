package org.vinn.openECommerce.api.user.staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vinn.openECommerce.api.user.staff.model.Staff;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Object> findByUsername(String username);
}
