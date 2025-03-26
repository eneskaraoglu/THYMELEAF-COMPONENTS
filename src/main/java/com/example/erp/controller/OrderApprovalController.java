package com.example.erp.controller;

import com.example.erp.model.ApprovalStatus;
import com.example.erp.model.OrderApproval;
import com.example.erp.service.OrderApprovalService;
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
@RequestMapping("/order-approvals")
public class OrderApprovalController {
    
    private final OrderApprovalService orderApprovalService;
    private static final int PAGE_SIZE = 10;
    
    public OrderApprovalController(OrderApprovalService orderApprovalService) {
        this.orderApprovalService = orderApprovalService;
    }
    
    @GetMapping
    public String listApprovals(
            @RequestParam(required = false) String orderNumber,
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false) String salesRepresentative,
            @RequestParam(required = false) ApprovalStatus status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        
        Page<OrderApproval> approvals = orderApprovalService.findByFilters(
                orderNumber, customerName, salesRepresentative, 
                status, fromDate, toDate, PageRequest.of(page, size));
        
        model.addAttribute("approvals", approvals);
        model.addAttribute("pageTitle", "Sipariş Onayları");
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", approvals.getTotalPages());
        model.addAttribute("totalItems", approvals.getTotalElements());
        
        // Filtreleri modele ekle
        model.addAttribute("orderNumber", orderNumber);
        model.addAttribute("customerName", customerName);
        model.addAttribute("salesRepresentative", salesRepresentative);
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
        
        return "fragments/order-approvals/list";
    }
    
    @GetMapping("/{id}")
    public String viewApproval(@PathVariable Long id, Model model) {
        model.addAttribute("approval", orderApprovalService.findById(id));
        model.addAttribute("pageTitle", "Sipariş Onay Detayı");
        return "fragments/order-approvals/view";
    }
    
    @GetMapping("/new")
    public String newApprovalForm(Model model) {
        model.addAttribute("approval", new OrderApproval());
        model.addAttribute("pageTitle", "Yeni Sipariş Talebi");
        return "fragments/order-approvals/form";
    }
    
    @PostMapping
    public String saveApproval(@ModelAttribute OrderApproval approval) {
        orderApprovalService.save(approval);
        return "redirect:/order-approvals";
    }
    
    @GetMapping("/{id}/edit")
    public String editApprovalForm(@PathVariable Long id, Model model) {
        model.addAttribute("approval", orderApprovalService.findById(id));
        model.addAttribute("pageTitle", "Sipariş Talebi Düzenle");
        return "fragments/order-approvals/form";
    }
    
    @GetMapping("/{id}/approve")
    public String approveRequest(@PathVariable Long id) {
        orderApprovalService.approve(id, "Admin"); // Normalde giriş yapmış kullanıcı bilgisi kullanılır
        return "redirect:/order-approvals";
    }
    
    @GetMapping("/{id}/reject")
    public String rejectRequest(@PathVariable Long id) {
        orderApprovalService.reject(id, "Admin"); // Normalde giriş yapmış kullanıcı bilgisi kullanılır
        return "redirect:/order-approvals";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteRequest(@PathVariable Long id) {
        orderApprovalService.deleteById(id);
        return "redirect:/order-approvals";
    }
} 