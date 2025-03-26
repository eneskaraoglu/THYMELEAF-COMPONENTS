package com.example.erp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class QualityControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String qcCode;
    
    @Enumerated(EnumType.STRING)
    private QualityControlStatus status;
    
    private Long productId;
    private String productCode;
    private String productName;
    
    private String batchNumber;
    private Long inspectorId;
    private String inspectorName;
    
    private LocalDate inspectionDate;
    private LocalDate manufacturingDate;
    
    @Column(length = 1000)
    private String notes;
    
    @OneToMany(mappedBy = "qualityControl", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QualityCriteria> criteriaList = new ArrayList<>();
    
    // Helper method to add criteria
    public void addCriteria(QualityCriteria criteria) {
        criteriaList.add(criteria);
        criteria.setQualityControl(this);
    }
    
    // Helper method to remove criteria
    public void removeCriteria(QualityCriteria criteria) {
        criteriaList.remove(criteria);
        criteria.setQualityControl(null);
    }
}


