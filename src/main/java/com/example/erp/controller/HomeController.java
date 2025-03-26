package com.example.erp.controller;

import com.example.erp.model.ApprovalStatus;
import com.example.erp.service.OrderApprovalService;
import com.example.erp.service.PurchaseApprovalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    private final PurchaseApprovalService purchaseApprovalService;
    private final OrderApprovalService orderApprovalService;
    
    public HomeController(PurchaseApprovalService purchaseApprovalService, 
                         OrderApprovalService orderApprovalService) {
        this.purchaseApprovalService = purchaseApprovalService;
        this.orderApprovalService = orderApprovalService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageTitle", "Ana Sayfa");
        
        // Dashboard için bazı temel veriler
        model.addAttribute("pendingPurchaseApprovals", 
                purchaseApprovalService.findByStatus(ApprovalStatus.PENDING).size());
        model.addAttribute("pendingOrderApprovals", 
                orderApprovalService.findByStatus(ApprovalStatus.PENDING).size());
        model.addAttribute("urgentPurchases", 
                purchaseApprovalService.findUrgentRequests().size());
        model.addAttribute("urgentDeliveries", 
                orderApprovalService.findUrgentDeliveries().size());
        
        return "index";
    }

    // @GetMapping("/purchase-approvals") metodunu da kaldırmamız gerekebilir
    // çünkü benzer bir çakışma PurchaseApprovalController ile de olabilir
    // ancak şu anda sadece hata veren orderApprovals metodunu kaldırıyoruz
} 