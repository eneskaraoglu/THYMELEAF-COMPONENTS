package com.example.erp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilter {
    private String code;
    private String name;
    private SearchCriteria nameSearchCriteria = SearchCriteria.CONTAINS;
    
    private Double price;
    private SearchCriteria priceSearchCriteria = SearchCriteria.EQUALS;
    
    private Integer stock;
    private SearchCriteria stockSearchCriteria = SearchCriteria.EQUALS;
    
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
} 