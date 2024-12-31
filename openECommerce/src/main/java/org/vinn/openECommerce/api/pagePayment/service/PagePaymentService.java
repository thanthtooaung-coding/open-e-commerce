package org.vinn.openECommerce.api.pagePayment.service;

import org.vinn.openECommerce.api.pagePayment.dto.AddPagePaymentRequest;
import org.vinn.openECommerce.api.pagePayment.dto.PagePaymentDTO;

public interface PagePaymentService {
    PagePaymentDTO createPagePayment(AddPagePaymentRequest payment);
}
