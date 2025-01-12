package org.vinn.openECommerce.api.pagePayment.service.impl;

import org.springframework.stereotype.Service;
import org.vinn.openECommerce.api.pagePayment.dto.AddPagePaymentRequest;
import org.vinn.openECommerce.api.pagePayment.dto.PagePaymentDTO;
import org.vinn.openECommerce.api.pagePayment.model.PagePayment;
import org.vinn.openECommerce.api.pagePayment.model.PaymentMethod;
import org.vinn.openECommerce.api.pagePayment.repository.PagePaymentRepository;
import org.vinn.openECommerce.api.pagePayment.repository.PaymentMethodRepository;
import org.vinn.openECommerce.api.pagePayment.service.PagePaymentService;
import org.vinn.openECommerce.api.pagePayment.util.TransactionIdGenerator;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class PagePaymentServiceImpl implements PagePaymentService {

    private final PagePaymentRepository pagePaymentRepository;
    private final TransactionIdGenerator transactionIdGenerator;
    private final PaymentMethodRepository paymentMethodRepository;

    public PagePaymentServiceImpl(PagePaymentRepository pagePaymentRepository, TransactionIdGenerator transactionIdGenerator, PaymentMethodRepository paymentMethodRepository) {
        this.pagePaymentRepository = pagePaymentRepository;
        this.transactionIdGenerator = transactionIdGenerator;
        this.paymentMethodRepository = paymentMethodRepository;
    }


    @Override
    public PagePaymentDTO createPagePayment(AddPagePaymentRequest paymentDto) {
        PagePayment pagePayment = new PagePayment();
        pagePayment.setAmount(BigDecimal.valueOf(1.00));
        pagePayment.setStatus("Completed");
        pagePayment.setTransactionId(transactionIdGenerator.generateTransactionId());
        PaymentMethod paymentMethod = new PaymentMethod("Cash");
        paymentMethod = paymentMethodRepository.save(paymentMethod);
        pagePayment.setPaymentMethod(paymentMethod);
        pagePayment.setPageOwner(paymentDto.getPageOwner());
        pagePayment.setCreatedAt(Instant.now());

        PagePayment savedPagePayment = pagePaymentRepository.save(pagePayment);

        return new PagePaymentDTO(savedPagePayment);
    }
}
