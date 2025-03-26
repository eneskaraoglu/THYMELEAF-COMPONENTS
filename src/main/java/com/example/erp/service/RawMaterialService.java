package com.example.erp.service;

import com.example.erp.model.RawMaterial;
import com.example.erp.model.RawMaterialFilter;
import com.example.erp.repository.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RawMaterialService {

    private final RawMaterialRepository rawMaterialRepository;

    @Autowired
    public RawMaterialService(RawMaterialRepository rawMaterialRepository) {
        this.rawMaterialRepository = rawMaterialRepository;
    }

    public List<RawMaterial> findAll() {
        return rawMaterialRepository.findAll();
    }

    public RawMaterial findById(Long id) {
        return rawMaterialRepository.findById(id).orElse(null);
    }

    public RawMaterial save(RawMaterial rawMaterial) {
        return rawMaterialRepository.save(rawMaterial);
    }

    public void deleteById(Long id) {
        rawMaterialRepository.deleteById(id);
    }

    public List<RawMaterial> findByFilter(RawMaterialFilter filter) {
        Set<RawMaterial> filteredMaterials = new HashSet<>();
        boolean filterApplied = false;

        // Apply name filter if provided
        if (filter.getName() != null && !filter.getName().isEmpty()) {
            filteredMaterials.addAll(rawMaterialRepository.findByNameContainingIgnoreCase(filter.getName()));
            filterApplied = true;
        }

        // Apply category filter if provided
        if (filter.getCategory() != null && !filter.getCategory().isEmpty()) {
            List<RawMaterial> categoryMaterials = rawMaterialRepository.findByCategory(filter.getCategory());
            if (filterApplied) {
                filteredMaterials.retainAll(new HashSet<>(categoryMaterials));
            } else {
                filteredMaterials.addAll(categoryMaterials);
                filterApplied = true;
            }
        }

        // Apply supplier filter if provided
        if (filter.getSupplier() != null && !filter.getSupplier().isEmpty()) {
            List<RawMaterial> supplierMaterials = rawMaterialRepository.findBySupplier(filter.getSupplier());
            if (filterApplied) {
                filteredMaterials.retainAll(new HashSet<>(supplierMaterials));
            } else {
                filteredMaterials.addAll(supplierMaterials);
                filterApplied = true;
            }
        }

        // Apply low stock filter if selected
        if (filter.getLowStock() != null && filter.getLowStock()) {
            List<RawMaterial> lowStockMaterials = rawMaterialRepository.findMaterialsBelowMinimumStock();
            if (filterApplied) {
                filteredMaterials.retainAll(new HashSet<>(lowStockMaterials));
            } else {
                filteredMaterials.addAll(lowStockMaterials);
                filterApplied = true;
            }
        }

        // Apply needs reorder filter if selected
        if (filter.getNeedsReorder() != null && filter.getNeedsReorder()) {
            List<RawMaterial> reorderMaterials = rawMaterialRepository.findMaterialsNeedingReorder();
            if (filterApplied) {
                filteredMaterials.retainAll(new HashSet<>(reorderMaterials));
            } else {
                filteredMaterials.addAll(reorderMaterials);
                filterApplied = true;
            }
        }

        // If no filters applied, return all materials
        if (!filterApplied) {
            return rawMaterialRepository.findAll();
        }

        return new ArrayList<>(filteredMaterials);
    }

    public List<RawMaterial> findMaterialsNeedingReorder() {
        return rawMaterialRepository.findMaterialsNeedingReorder();
    }

    public List<RawMaterial> findMaterialsBelowMinimumStock() {
        return rawMaterialRepository.findMaterialsBelowMinimumStock();
    }

    public List<String> findAllCategories() {
        List<RawMaterial> materials = rawMaterialRepository.findAll();
        Set<String> categories = new HashSet<>();
        
        for (RawMaterial material : materials) {
            if (material.getCategory() != null && !material.getCategory().isEmpty()) {
                categories.add(material.getCategory());
            }
        }
        
        return new ArrayList<>(categories);
    }

    public List<String> findAllSuppliers() {
        List<RawMaterial> materials = rawMaterialRepository.findAll();
        Set<String> suppliers = new HashSet<>();
        
        for (RawMaterial material : materials) {
            if (material.getSupplier() != null && !material.getSupplier().isEmpty()) {
                suppliers.add(material.getSupplier());
            }
        }
        
        return new ArrayList<>(suppliers);
    }
}
