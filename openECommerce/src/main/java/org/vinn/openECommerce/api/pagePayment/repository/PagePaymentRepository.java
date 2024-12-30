package org.vinn.openECommerce.api.pagePayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vinn.openECommerce.api.pagePayment.model.PagePayment;

public interface PagePaymentRepository extends JpaRepository<PagePayment, Long> {
}
