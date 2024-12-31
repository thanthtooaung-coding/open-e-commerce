package org.vinn.openECommerce.api.analytics.systemAdmin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vinn.openECommerce.api.analytics.systemAdmin.service.SystemAdminAnalyticsService;

import java.util.Map;

@RestController
@RequestMapping("${api.base.path}/system-admins-analytics")
public class SystemAdminAnalyticsController {

    private final SystemAdminAnalyticsService analyticsService;

    public SystemAdminAnalyticsController(SystemAdminAnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/platform-metrics")
    public Map<String, Object> getPlatformMetrics() {
        return analyticsService.getPlatformMetrics();
    }

    @GetMapping("/page-statistics")
    public Map<String, Object> getPageStatistics() {
        return analyticsService.getPageStatistics();
    }

    @GetMapping("/payment-statistics")
    public Map<String, Object> getPaymentStatistics() {
        return analyticsService.getPaymentStatistics();
    }

    @GetMapping("/user-activity")
    public Map<String, Object> getUserActivity() {
        return analyticsService.getUserActivity();
    }
}
