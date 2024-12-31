package org.vinn.openECommerce.api.pagePayment.dto;

import lombok.Getter;
import lombok.Setter;
import org.vinn.openECommerce.api.pagePayment.model.PaymentMethod;
import org.vinn.openECommerce.api.user.pageOwner.model.PageOwner;
import org.vinn.openECommerce.api.user.systemAdmin.model.SystemAdmin;

import java.math.BigDecimal;

@Getter
@Setter
public class AddPagePaymentRequest {

    private Long id;

    private BigDecimal amount;

    private String status;

    private String transactionId;

    private SystemAdmin systemAdmin;

    private PageOwner pageOwner;

    private PaymentMethod paymentMethod;
}
