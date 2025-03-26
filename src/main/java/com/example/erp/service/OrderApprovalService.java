package com.example.erp.service;

import com.example.erp.model.ApprovalStatus;
import com.example.erp.model.OrderApproval;
import com.example.erp.repository.OrderApprovalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderApprovalService {
    
    private final OrderApprovalRepository orderApprovalRepository;
    
    public OrderApprovalService(OrderApprovalRepository orderApprovalRepository) {
        this.orderApprovalRepository = orderApprovalRepository;
    }
    
    public List<OrderApproval> findAll() {
        return orderApprovalRepository.findAll();
    }
    
    public OrderApproval findById(Long id) {
        return orderApprovalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sipariş onayı bulunamadı: " + id));
    }
    
    public OrderApproval save(OrderApproval approval) {
        return orderApprovalRepository.save(approval);
    }
    
    public void deleteById(Long id) {
        orderApprovalRepository.deleteById(id);
    }
    
    public List<OrderApproval> findUrgentDeliveries() {
        return orderApprovalRepository.findUrgentDeliveries();
    }
    
    public Page<OrderApproval> findByFilters(
            String orderNumber, 
            String customerName, 
            String salesRepresentative, 
            ApprovalStatus status, 
            LocalDate fromDate, 
            LocalDate toDate,
            Pageable pageable) {
        
        return orderApprovalRepository.findByFilters(
                orderNumber, customerName, salesRepresentative, 
                status, fromDate, toDate, pageable);
    }
    
    public OrderApproval approve(Long id, String approvedBy) {
        OrderApproval approval = findById(id);
        approval.setStatus(ApprovalStatus.APPROVED);
        approval.setApprovedBy(approvedBy);
        approval.setApprovalDate(LocalDate.now());
        return orderApprovalRepository.save(approval);
    }
    
    public OrderApproval reject(Long id, String rejectedBy) {
        OrderApproval approval = findById(id);
        approval.setStatus(ApprovalStatus.REJECTED);
        approval.setApprovedBy(rejectedBy); // Using same field for rejected by
        approval.setApprovalDate(LocalDate.now());
        return orderApprovalRepository.save(approval);
    }
    
    public List<OrderApproval> findByStatus(ApprovalStatus status) {
        return orderApprovalRepository.findByStatus(status);
    }
    
    public List<OrderApproval> findByDateRange(LocalDate start, LocalDate end) {
        return orderApprovalRepository.findByOrderDateBetween(start, end);
    }
} 