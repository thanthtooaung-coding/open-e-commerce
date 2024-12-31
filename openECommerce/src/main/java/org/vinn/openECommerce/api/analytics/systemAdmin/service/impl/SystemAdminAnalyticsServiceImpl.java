package org.vinn.openECommerce.api.analytics.systemAdmin.service.impl;

import org.springframework.stereotype.Service;
import org.vinn.openECommerce.api.analytics.systemAdmin.service.SystemAdminAnalyticsService;
import org.vinn.openECommerce.api.page.repository.PageRepository;
import org.vinn.openECommerce.api.pagePayment.repository.PagePaymentRepository;
import org.vinn.openECommerce.api.user.pageOwner.repository.PageOwnerRepository;
import org.vinn.openECommerce.api.user.systemAdmin.repository.SystemAdminRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service
public class SystemAdminAnalyticsServiceImpl implements SystemAdminAnalyticsService {

    private final PageRepository pageRepository;
    private final PagePaymentRepository pagePaymentRepository;
    private final SystemAdminRepository systemAdminRepository;
    private final PageOwnerRepository pageOwnerRepository;

    public SystemAdminAnalyticsServiceImpl(
            PageRepository pageRepository,
            PagePaymentRepository pagePaymentRepository,
            SystemAdminRepository systemAdminRepository,
            PageOwnerRepository pageOwnerRepository) {
        this.pageRepository = pageRepository;
        this.pagePaymentRepository = pagePaymentRepository;
        this.systemAdminRepository = systemAdminRepository;
        this.pageOwnerRepository = pageOwnerRepository;
    }

    @Override
    public Map<String, Object> getPlatformMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("totalPages", pageRepository.count());
        metrics.put("activePages", pageRepository.countByActive(true));
        metrics.put("newPageOwnersThisMonth", pageOwnerRepository.countByCreatedAtAfter(Instant.now().minus(30, ChronoUnit.DAYS)));
        metrics.put("totalPayments", pagePaymentRepository.count());
        metrics.put("completedPayments", pagePaymentRepository.countByStatus("COMPLETED"));
        metrics.put("pendingPayments", pagePaymentRepository.countByStatus("PENDING"));
        metrics.put("totalAdmins", systemAdminRepository.count());
        metrics.put("totalPageOwners", pageOwnerRepository.count());
        return metrics;
    }

    @Override
    public Map<String, Object> getPageStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("pagesOverTime", pageRepository.getPagesOverTime());
        stats.put("topPageOwners", pageRepository.getTopPageOwners());
        return stats;
    }

    @Override
    public Map<String, Object> getPaymentStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalRevenue", pagePaymentRepository.getTotalRevenue());
        stats.put("revenueTrends", pagePaymentRepository.getRevenueTrends());
        stats.put("paymentStatusBreakdown", pagePaymentRepository.getPaymentStatusBreakdown());
        return stats;
    }

    @Override
    public Map<String, Object> getUserActivity() {
        Map<String, Object> activity = new HashMap<>();
//        activity.put("loginsOverTime", systemAdminRepository.getLoginsOverTime());
//        activity.put("recentPages", pageRepository.getRecentPages());
//        activity.put("recentPayments", pagePaymentRepository.getRecentPayments());
        return activity;
    }
}
