package com.example.erp.model;

public enum ApprovalStatus {
    PENDING("Beklemede"),
    APPROVED("Onaylandı"),
    REJECTED("Reddedildi"),
    DELAYED("Ertelendi"),
    CANCELLED("İptal Edildi");

    private final String displayValue;

    ApprovalStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
} 