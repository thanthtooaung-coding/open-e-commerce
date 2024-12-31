package org.vinn.openECommerce.api.pagePayment.dto;

import lombok.Getter;
import lombok.Setter;
import org.vinn.openECommerce.api.pagePayment.model.PagePayment;
import org.vinn.openECommerce.api.user.pageOwner.model.PageOwner;

import java.math.BigDecimal;

@Getter
@Setter
public class PagePaymentDTO {

    private BigDecimal amount;
    private String status;
    private String transactionId;
    private PageOwner pageOwner;

    public PagePaymentDTO() {}

    public PagePaymentDTO(PagePayment pagePayment) {
        this.amount = pagePayment.getAmount();
        this.status = pagePayment.getStatus();
        this.transactionId = pagePayment.getTransactionId();
        this.pageOwner = pagePayment.getPageOwner();
    }
}
