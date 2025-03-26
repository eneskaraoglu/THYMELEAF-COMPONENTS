package com.example.erp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class QualityCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String expectedValue;
    private String measuredValue;
    private Boolean passed;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quality_control_id")
    private QualityControl qualityControl;
}