package org.vinn.openECommerce.api.pagePayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vinn.openECommerce.api.pagePayment.model.PagePayment;

import java.math.BigDecimal;
import java.util.List;

public interface PagePaymentRepository extends JpaRepository<PagePayment, Long> {
    @Query("SELECT SUM(p.amount) FROM PagePayment p")
    BigDecimal getTotalRevenue();

    @Query("SELECT p.status, COUNT(p) FROM PagePayment p GROUP BY p.status")
    List<Object[]> getPaymentStatusBreakdown();

    @Query("SELECT p.createdAt, SUM(p.amount) FROM PagePayment p GROUP BY p.createdAt ORDER BY p.createdAt")
    List<Object[]> getRevenueTrends();

    long countByStatus(String status);
}
