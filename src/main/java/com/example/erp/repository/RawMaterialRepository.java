package com.example.erp.repository;

import com.example.erp.model.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
    
    List<RawMaterial> findByNameContainingIgnoreCase(String name);
    
    List<RawMaterial> findByCategory(String category);
    
    List<RawMaterial> findBySupplier(String supplier);
    
    @Query("SELECT r FROM RawMaterial r WHERE r.quantity <= r.reorderPoint")
    List<RawMaterial> findMaterialsNeedingReorder();
    
    @Query("SELECT r FROM RawMaterial r WHERE r.quantity <= r.minimumStock")
    List<RawMaterial> findMaterialsBelowMinimumStock();
}