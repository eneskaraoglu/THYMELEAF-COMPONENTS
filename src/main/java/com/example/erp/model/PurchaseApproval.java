package com.example.erp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "purchase_approvals")
public class PurchaseApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requestNumber;
    private String requesterName;
    private String department;
    private String supplierName;
    
    @Enumerated(EnumType.STRING)
    private ApprovalStatus status = ApprovalStatus.PENDING;
    
    private LocalDate requestDate = LocalDate.now();
    private String description;
    private Double totalAmount;
    private LocalDate deliveryDate;
    private String notes;
    private String category;
    private String approvedBy;
    private LocalDate approvalDate;
} 