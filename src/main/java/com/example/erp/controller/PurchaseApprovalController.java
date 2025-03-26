package com.example.erp.controller;

import com.example.erp.model.ApprovalStatus;
import com.example.erp.model.PurchaseApproval;
import com.example.erp.service.PurchaseApprovalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/purchase-approvals")
public class PurchaseApprovalController {
    
    private final PurchaseApprovalService purchaseApprovalService;
    private static final int PAGE_SIZE = 10;
    
    public PurchaseApprovalController(PurchaseApprovalService purchaseApprovalService) {
        this.purchaseApprovalService = purchaseApprovalService;
    }
    
    @GetMapping
    public String listApprovals(
            @RequestParam(required = false) String requestNumber,
            @RequestParam(required = false) String requesterName,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String supplierName,
            @RequestParam(required = false) ApprovalStatus status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        
        Page<PurchaseApproval> approvals = purchaseApprovalService.findByFilters(
                requestNumber, requesterName, department, supplierName, 
                status, fromDate, toDate, PageRequest.of(page, size));
        
        model.addAttribute("approvals", approvals);
        model.addAttribute("pageTitle", "Satın Alma Onayları");
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", approvals.getTotalPages());
        model.addAttribute("totalItems", approvals.getTotalElements());
        
        // Filtreleri modele ekle
        model.addAttribute("requestNumber", requestNumber);
        model.addAttribute("requesterName", requesterName);
        model.addAttribute("department", department);
        model.addAttribute("supplierName", supplierName);
        model.addAttribute("status", status);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("size", size);
        
        if (approvals.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, approvals.getTotalPages() - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        
        return "fragments/purchase-approvals/list";
    }
    
    @GetMapping("/{id}")
    public String viewApproval(@PathVariable Long id, Model model) {
        model.addAttribute("approval", purchaseApprovalService.findById(id));
        model.addAttribute("pageTitle", "Satın Alma Onay Detayı");
        return "fragments/purchase-approvals/view";
    }
    
    @GetMapping("/new")
    public String newApprovalForm(Model model) {
        model.addAttribute("approval", new PurchaseApproval());
        model.addAttribute("pageTitle", "Yeni Satın Alma Talebi");
        return "fragments/purchase-approvals/form";
    }
    
    @PostMapping
    public String saveApproval(@ModelAttribute PurchaseApproval approval) {
        purchaseApprovalService.save(approval);
        return "redirect:/purchase-approvals";
    }
    
    @GetMapping("/{id}/edit")
    public String editApprovalForm(@PathVariable Long id, Model model) {
        model.addAttribute("approval", purchaseApprovalService.findById(id));
        model.addAttribute("pageTitle", "Satın Alma Talebi Düzenle");
        return "fragments/purchase-approvals/form";
    }
    
    @GetMapping("/{id}/approve")
    public String approveRequest(@PathVariable Long id) {
        purchaseApprovalService.approve(id, "Admin"); // Normalde giriş yapmış kullanıcı bilgisi kullanılır
        return "redirect:/purchase-approvals";
    }
    
    @GetMapping("/{id}/reject")
    public String rejectRequest(@PathVariable Long id) {
        purchaseApprovalService.reject(id, "Admin"); // Normalde giriş yapmış kullanıcı bilgisi kullanılır
        return "redirect:/purchase-approvals";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteRequest(@PathVariable Long id) {
        purchaseApprovalService.deleteById(id);
        return "redirect:/purchase-approvals";
    }
} 