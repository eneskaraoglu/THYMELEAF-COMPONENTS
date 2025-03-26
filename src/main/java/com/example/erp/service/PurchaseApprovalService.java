package com.example.erp.service;

import com.example.erp.model.ApprovalStatus;
import com.example.erp.model.PurchaseApproval;
import com.example.erp.repository.PurchaseApprovalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseApprovalService {
    
    private final PurchaseApprovalRepository purchaseApprovalRepository;
    
    public PurchaseApprovalService(PurchaseApprovalRepository purchaseApprovalRepository) {
        this.purchaseApprovalRepository = purchaseApprovalRepository;
    }
    
    public List<PurchaseApproval> findAll() {
        return purchaseApprovalRepository.findAll();
    }
    
    public PurchaseApproval findById(Long id) {
        return purchaseApprovalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Satın alma onayı bulunamadı: " + id));
    }
    
    public PurchaseApproval save(PurchaseApproval approval) {
        return purchaseApprovalRepository.save(approval);
    }
    
    public void deleteById(Long id) {
        purchaseApprovalRepository.deleteById(id);
    }
    
    public List<PurchaseApproval> findUrgentRequests() {
        return purchaseApprovalRepository.findUrgentRequests();
    }
    
    public Page<PurchaseApproval> findByFilters(
            String requestNumber, 
            String requesterName, 
            String department,
            String supplierName, 
            ApprovalStatus status, 
            LocalDate fromDate, 
            LocalDate toDate,
            Pageable pageable) {
        
        return purchaseApprovalRepository.findByFilters(
                requestNumber, requesterName, department, supplierName, 
                status, fromDate, toDate, pageable);
    }
    
    public PurchaseApproval approve(Long id, String approvedBy) {
        PurchaseApproval approval = findById(id);
        approval.setStatus(ApprovalStatus.APPROVED);
        approval.setApprovedBy(approvedBy);
        approval.setApprovalDate(LocalDate.now());
        return purchaseApprovalRepository.save(approval);
    }
    
    public PurchaseApproval reject(Long id, String rejectedBy) {
        PurchaseApproval approval = findById(id);
        approval.setStatus(ApprovalStatus.REJECTED);
        approval.setApprovedBy(rejectedBy); // Using same field for rejected by
        approval.setApprovalDate(LocalDate.now());
        return purchaseApprovalRepository.save(approval);
    }
    
    public List<PurchaseApproval> findByStatus(ApprovalStatus status) {
        return purchaseApprovalRepository.findByStatus(status);
    }
    
    public List<PurchaseApproval> findByDateRange(LocalDate start, LocalDate end) {
        return purchaseApprovalRepository.findByRequestDateBetween(start, end);
    }
} 