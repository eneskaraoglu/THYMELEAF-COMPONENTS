package com.example.erp.repository;

import com.example.erp.model.QualityControl;
import com.example.erp.model.QualityControlStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface QualityControlRepository extends JpaRepository<QualityControl, Long> {
    
    @Query("SELECT qc FROM QualityControl qc WHERE " +
           "(:qcCode IS NULL OR qc.qcCode LIKE %:qcCode%) AND " +
           "(:productCode IS NULL OR qc.productCode LIKE %:productCode%) AND " +
           "(:status IS NULL OR qc.status = :status) AND " +
           "(:inspectorId IS NULL OR qc.inspectorId = :inspectorId) AND " +
           "(:batchNumber IS NULL OR qc.batchNumber LIKE %:batchNumber%) AND " +
           "(:startDate IS NULL OR qc.inspectionDate >= :startDate) AND " +
           "(:endDate IS NULL OR qc.inspectionDate <= :endDate)")
    Page<QualityControl> findByFilters(@Param("qcCode") String qcCode,
                                      @Param("productCode") String productCode,
                                      @Param("status") QualityControlStatus status,
                                      @Param("inspectorId") Long inspectorId,
                                      @Param("batchNumber") String batchNumber,
                                      @Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate,
                                      Pageable pageable);
}
