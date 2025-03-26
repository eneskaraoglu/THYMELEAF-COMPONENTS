package com.example.erp.repository;

import com.example.erp.model.ApprovalStatus;
import com.example.erp.model.PurchaseApproval;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseApprovalRepository extends JpaRepository<PurchaseApproval, Long> {
    
    @Query("SELECT p FROM PurchaseApproval p WHERE p.status = 'PENDING' AND p.requestDate <= CURRENT_DATE")
    List<PurchaseApproval> findUrgentRequests();
    
    @Query("SELECT p FROM PurchaseApproval p WHERE " +
           "(:requestNumber IS NULL OR p.requestNumber LIKE %:requestNumber%) AND " +
           "(:requesterName IS NULL OR p.requesterName LIKE %:requesterName%) AND " +
           "(:department IS NULL OR p.department LIKE %:department%) AND " +
           "(:supplierName IS NULL OR p.supplierName LIKE %:supplierName%) AND " +
           "(:status IS NULL OR p.status = :status) AND " +
           "(:fromDate IS NULL OR p.requestDate >= :fromDate) AND " +
           "(:toDate IS NULL OR p.requestDate <= :toDate)")
    Page<PurchaseApproval> findByFilters(
        @Param("requestNumber") String requestNumber,
        @Param("requesterName") String requesterName,
        @Param("department") String department,
        @Param("supplierName") String supplierName,
        @Param("status") ApprovalStatus status,
        @Param("fromDate") LocalDate fromDate,
        @Param("toDate") LocalDate toDate,
        Pageable pageable
    );
    
    List<PurchaseApproval> findByStatus(ApprovalStatus status);
    
    List<PurchaseApproval> findByRequestDateBetween(LocalDate start, LocalDate end);
} 