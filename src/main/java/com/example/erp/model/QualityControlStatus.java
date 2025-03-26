package com.example.erp.model;

public enum QualityControlStatus {
    PENDING("Beklemede"),
    IN_PROGRESS("İşlemde"),
    APPROVED("Onaylandı"),
    REJECTED("Reddedildi");
    
    private final String displayValue;
    
    QualityControlStatus(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}