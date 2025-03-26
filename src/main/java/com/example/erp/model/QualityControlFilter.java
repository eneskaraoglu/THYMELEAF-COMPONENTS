package com.example.erp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QualityControlFilter {
    private String qcCode;
    private String productCode;
    private QualityControlStatus status;
    private Long inspector;
    private String batchNumber;
    private LocalDate startDate;
    private LocalDate endDate;
}