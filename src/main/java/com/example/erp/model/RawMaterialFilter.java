package com.example.erp.model;

public class RawMaterialFilter {

    private String name;
    private String category;
    private String supplier;
    private Boolean lowStock = false;
    private Boolean needsReorder = false;

    public RawMaterialFilter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Boolean getLowStock() {
        return lowStock;
    }

    public void setLowStock(Boolean lowStock) {
        this.lowStock = lowStock;
    }

    public Boolean getNeedsReorder() {
        return needsReorder;
    }

    public void setNeedsReorder(Boolean needsReorder) {
        this.needsReorder = needsReorder;
    }
}
