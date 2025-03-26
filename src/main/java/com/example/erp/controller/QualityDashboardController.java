package com.example.erp.controller;

import com.example.erp.model.QualityControl;
import com.example.erp.model.QualityControlStatus;
import com.example.erp.service.QualityControlService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/quality-dashboard")
public class QualityDashboardController {
    
    private final QualityControlService qualityControlService;
    
    public QualityDashboardController(QualityControlService qualityControlService) {
        this.qualityControlService = qualityControlService;
    }
    
    @GetMapping
    public String dashboard(Model model) {
        // Tüm kalite kontrolleri getir
        List<QualityControl> allControls = qualityControlService.findAll();
        
        // İstatistikleri hesapla
        DashboardStats stats = calculateStats(allControls);
        model.addAttribute("stats", stats);
        
        // Son aktiviteleri getir (en yeni 5 kayıt)
        List<QualityControl> recentActivities = allControls.stream()
                .sorted(Comparator.comparing(QualityControl::getInspectionDate).reversed())
                .limit(5)
                .collect(Collectors.toList());
        model.addAttribute("recentActivities", recentActivities);
        
        // Aylık istatistik verilerini hazırla
        Map<String, Object> monthlyStatsData = prepareMonthlyStatsData(allControls);
        model.addAttribute("monthlyStatsData", monthlyStatsData);
        
        // Ürünlere göre kalite performansı verilerini hazırla
        Map<String, Object> productQualityData = prepareProductQualityData(allControls);
        model.addAttribute("productQualityData", productQualityData);
        
        // Kriterlere göre başarısızlık oranları verilerini hazırla
        Map<String, Object> criteriaFailureData = prepareCriteriaFailureData(allControls);
        model.addAttribute("criteriaFailureData", criteriaFailureData);
        
        return "quality-controls/quality-control-dashboard";
    }
    
    private DashboardStats calculateStats(List<QualityControl> controls) {
        int totalCount = controls.size();
        
        int approvedCount = (int) controls.stream()
                .filter(qc -> qc.getStatus() == QualityControlStatus.APPROVED)
                .count();
        
        int rejectedCount = (int) controls.stream()
                .filter(qc -> qc.getStatus() == QualityControlStatus.REJECTED)
                .count();
        
        int pendingCount = (int) controls.stream()
                .filter(qc -> qc.getStatus() == QualityControlStatus.PENDING)
                .count();
        
        int inProgressCount = (int) controls.stream()
                .filter(qc -> qc.getStatus() == QualityControlStatus.IN_PROGRESS)
                .count();
        
        return new DashboardStats(totalCount, approvedCount, rejectedCount, pendingCount, inProgressCount);
    }
    
    private Map<String, Object> prepareMonthlyStatsData(List<QualityControl> controls) {
        // Son 6 ay için aylık veriler
        LocalDate now = LocalDate.now();
        List<String> labels = new ArrayList<>();
        List<Integer> approved = new ArrayList<>();
        List<Integer> rejected = new ArrayList<>();
        List<Integer> pending = new ArrayList<>();
        List<Integer> inProgress = new ArrayList<>();
        
        for (int i = 5; i >= 0; i--) {
            LocalDate monthDate = now.minusMonths(i);
            Month month = monthDate.getMonth();
            int year = monthDate.getYear();
            
            String monthLabel = month.getDisplayName(TextStyle.SHORT, Locale.getDefault()) + " " + year;
            labels.add(monthLabel);
            
            int monthApproved = 0;
            int monthRejected = 0;
            int monthPending = 0;
            int monthInProgress = 0;
            
            for (QualityControl qc : controls) {
                if (qc.getInspectionDate() != null && 
                    qc.getInspectionDate().getMonth() == month && 
                    qc.getInspectionDate().getYear() == year) {
                    
                    switch (qc.getStatus()) {
                        case APPROVED:
                            monthApproved++;
                            break;
                        case REJECTED:
                            monthRejected++;
                            break;
                        case PENDING:
                            monthPending++;
                            break;
                        case IN_PROGRESS:
                            monthInProgress++;
                            break;
                    }
                }
            }
            
            approved.add(monthApproved);
            rejected.add(monthRejected);
            pending.add(monthPending);
            inProgress.add(monthInProgress);
        }
        
        Map<String, Object> data = new HashMap<>();
        data.put("labels", labels);
        data.put("approved", approved);
        data.put("rejected", rejected);
        data.put("pending", pending);
        data.put("inProgress", inProgress);
        
        return data;
    }
    
    private Map<String, Object> prepareProductQualityData(List<QualityControl> controls) {
        // Ürünlere göre başarı oranlarını hesapla
        Map<String, Integer> productTotalMap = new HashMap<>();
        Map<String, Integer> productSuccessMap = new HashMap<>();
        
        for (QualityControl qc : controls) {
            String productName = qc.getProductName();
            if (productName == null) continue;
            
            productTotalMap.put(productName, productTotalMap.getOrDefault(productName, 0) + 1);
            
            if (qc.getStatus() == QualityControlStatus.APPROVED) {
                productSuccessMap.put(productName, productSuccessMap.getOrDefault(productName, 0) + 1);
            }
        }
        
        // Ürünleri başarı oranına göre sırala
        List<Map.Entry<String, Integer>> sortedProducts = new ArrayList<>(productTotalMap.entrySet());
        sortedProducts.sort((e1, e2) -> {
            double rate1 = (double) productSuccessMap.getOrDefault(e1.getKey(), 0) / e1.getValue() * 100;
            double rate2 = (double) productSuccessMap.getOrDefault(e2.getKey(), 0) / e2.getValue() * 100;
            return Double.compare(rate2, rate1); // Yüksekten düşüğe sıralama
        });
        
        // Sonuçları hazırla (en iyi 5 ürün)
        List<String> labels = new ArrayList<>();
        List<Double> successRates = new ArrayList<>();
        
        int count = 0;
        for (Map.Entry<String, Integer> entry : sortedProducts) {
            if (count >= 5) break;
            
            String productName = entry.getKey();
            int total = entry.getValue();
            int success = productSuccessMap.getOrDefault(productName, 0);
            double rate = (double) success / total * 100;
            
            labels.add(productName);
            successRates.add(Math.round(rate * 10) / 10.0); // 1 ondalık basamağa yuvarla
            
            count++;
        }
        
        Map<String, Object> data = new HashMap<>();
        data.put("labels", labels);
        data.put("successRates", successRates);
        
        return data;
    }
    
    private Map<String, Object> prepareCriteriaFailureData(List<QualityControl> controls) {
        // Kriterlere göre başarısızlık sayılarını hesapla
        Map<String, Integer> criteriaFailureMap = new HashMap<>();
        final int totalFailures = 0;
        
        for (QualityControl qc : controls) {
            if (qc.getCriteriaList() != null) {
                qc.getCriteriaList().forEach(criteria -> {
                    if (criteria.getPassed() != null && !criteria.getPassed()) {
                        String criteriaName = criteria.getName();
                        criteriaFailureMap.put(criteriaName, criteriaFailureMap.getOrDefault(criteriaName, 0) + 1);
                        //totalFailures++;
                    }
                });
            }
        }
        
        // En çok başarısız olan kriterleri sırala
        List<Map.Entry<String, Integer>> sortedCriteria = new ArrayList<>(criteriaFailureMap.entrySet());
        sortedCriteria.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        
        // Sonuçları hazırla (en kötü 5 kriter)
        List<String> labels = new ArrayList<>();
        List<Double> failureRates = new ArrayList<>();
        
        int count = 0;
        for (Map.Entry<String, Integer> entry : sortedCriteria) {
            if (count >= 5) break;
            
            String criteriaName = entry.getKey();
            int failures = entry.getValue();
            double rate = (double) failures / totalFailures * 100;
            
            labels.add(criteriaName);
            failureRates.add(Math.round(rate * 10) / 10.0); // 1 ondalık basamağa yuvarla
            
            count++;
        }
        
        Map<String, Object> data = new HashMap<>();
        data.put("labels", labels);
        data.put("failureRates", failureRates);
        
        return data;
    }
    
    // Dashboard istatistikleri için iç sınıf
    public static class DashboardStats {
        private int totalCount;
        private int approvedCount;
        private int rejectedCount;
        private int pendingCount;
        private int inProgressCount;
        
        public DashboardStats(int totalCount, int approvedCount, int rejectedCount, int pendingCount, int inProgressCount) {
            this.totalCount = totalCount;
            this.approvedCount = approvedCount;
            this.rejectedCount = rejectedCount;
            this.pendingCount = pendingCount;
            this.inProgressCount = inProgressCount;
        }
        
        public int getTotalCount() {
            return totalCount;
        }
        
        public int getApprovedCount() {
            return approvedCount;
        }
        
        public int getRejectedCount() {
            return rejectedCount;
        }
        
        public int getPendingCount() {
            return pendingCount;
        }
        
        public int getInProgressCount() {
            return inProgressCount;
        }
    }
}