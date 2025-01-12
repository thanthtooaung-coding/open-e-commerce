package org.vinn.openECommerce.api.pagePayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vinn.openECommerce.api.pagePayment.model.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
