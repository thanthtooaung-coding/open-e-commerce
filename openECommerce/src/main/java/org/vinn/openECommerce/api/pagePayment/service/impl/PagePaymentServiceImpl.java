package org.vinn.openECommerce.api.pagePayment.service.impl;

import org.springframework.stereotype.Service;
import org.vinn.openECommerce.api.pagePayment.dto.AddPagePaymentRequest;
import org.vinn.openECommerce.api.pagePayment.dto.PagePaymentDTO;
import org.vinn.openECommerce.api.pagePayment.model.PagePayment;
import org.vinn.openECommerce.api.pagePayment.model.PaymentMethod;
import org.vinn.openECommerce.api.pagePayment.repository.PagePaymentRepository;
import org.vinn.openECommerce.api.pagePayment.service.PagePaymentService;
import org.vinn.openECommerce.api.pagePayment.util.TransactionIdGenerator;

import java.math.BigDecimal;

@Service
public class PagePaymentServiceImpl implements PagePaymentService {

    private final PagePaymentRepository pagePaymentRepository;
    private final TransactionIdGenerator transactionIdGenerator;

    public PagePaymentServiceImpl(PagePaymentRepository pagePaymentRepository, TransactionIdGenerator transactionIdGenerator) {
        this.pagePaymentRepository = pagePaymentRepository;
        this.transactionIdGenerator = transactionIdGenerator;
    }


    @Override
    public PagePaymentDTO createPagePayment(AddPagePaymentRequest paymentDto) {
        PagePayment pagePayment = new PagePayment();
        pagePayment.setAmount(BigDecimal.valueOf(1.00));
        pagePayment.setStatus("Completed");
        pagePayment.setTransactionId(transactionIdGenerator.generateTransactionId());
        pagePayment.setPaymentMethod(new PaymentMethod("Cash"));
        pagePayment.setPageOwner(paymentDto.getPageOwner());

        PagePayment savedPagePayment = pagePaymentRepository.save(pagePayment);

        return new PagePaymentDTO(savedPagePayment);
    }
}
