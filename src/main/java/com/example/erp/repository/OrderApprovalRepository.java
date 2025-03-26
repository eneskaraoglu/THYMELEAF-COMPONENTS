package com.example.erp.repository;

import com.example.erp.model.ApprovalStatus;
import com.example.erp.model.OrderApproval;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderApprovalRepository extends JpaRepository<OrderApproval, Long> {
    
    @Query("SELECT o FROM OrderApproval o WHERE o.status = 'PENDING' AND o.deliveryDate <= CURRENT_DATE")
    List<OrderApproval> findUrgentDeliveries();
    
    @Query("SELECT o FROM OrderApproval o WHERE " +
           "(:orderNumber IS NULL OR o.orderNumber LIKE %:orderNumber%) AND " +
           "(:customerName IS NULL OR o.customerName LIKE %:customerName%) AND " +
           "(:salesRepresentative IS NULL OR o.salesRepresentative LIKE %:salesRepresentative%) AND " +
           "(:status IS NULL OR o.status = :status) AND " +
           "(:fromDate IS NULL OR o.orderDate >= :fromDate) AND " +
           "(:toDate IS NULL OR o.orderDate <= :toDate)")
    Page<OrderApproval> findByFilters(
        @Param("orderNumber") String orderNumber,
        @Param("customerName") String customerName,
        @Param("salesRepresentative") String salesRepresentative,
        @Param("status") ApprovalStatus status,
        @Param("fromDate") LocalDate fromDate,
        @Param("toDate") LocalDate toDate,
        Pageable pageable
    );
    
    List<OrderApproval> findByStatus(ApprovalStatus status);
    
    List<OrderApproval> findByOrderDateBetween(LocalDate start, LocalDate end);
} 