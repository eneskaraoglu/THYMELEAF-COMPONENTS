package com.example.erp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String code;
    private Double price;
    private Integer stock;
    private String description;
    
    // Yeni alanlar
    private LocalDate manufacturingDate; // Üretim tarihi
    private LocalDate expiryDate; // Son kullanma tarihi
} 