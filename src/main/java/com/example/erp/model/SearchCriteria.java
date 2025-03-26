package com.example.erp.model;

public enum SearchCriteria {
    EQUALS("Eşit"),
    GREATER_THAN("Büyük"),
    LESS_THAN("Küçük"),
    CONTAINS("İçerir");

    private final String displayValue;

    SearchCriteria(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
} 