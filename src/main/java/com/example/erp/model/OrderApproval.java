package com.example.erp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "order_approvals")
public class OrderApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private String customerName;
    private String salesRepresentative;
    private Double totalAmount;
    
    @Enumerated(EnumType.STRING)
    private ApprovalStatus status = ApprovalStatus.PENDING;
    
    private LocalDate orderDate = LocalDate.now();
    private LocalDate deliveryDate;
    private String notes;
    private String paymentTerms;
    private String shippingMethod;
    private String approvedBy;
    private LocalDate approvalDate;
} 