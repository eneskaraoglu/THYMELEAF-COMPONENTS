package com.example.erp.service;

import com.example.erp.model.QualityControl;
import com.example.erp.model.QualityControlFilter;
import com.example.erp.repository.QualityControlRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QualityControlService {

    private final QualityControlRepository qualityControlRepository;

    public QualityControlService(QualityControlRepository qualityControlRepository) {
        this.qualityControlRepository = qualityControlRepository;
    }

    public List<QualityControl> findAll() {
        return qualityControlRepository.findAll();
    }

    public QualityControl findById(Long id) {
        return qualityControlRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kalite kontrol bulunamadı"));
    }

    @Transactional
    public QualityControl save(QualityControl qualityControl) {
        return qualityControlRepository.save(qualityControl);
    }

    public void deleteById(Long id) {
        qualityControlRepository.deleteById(id);
    }

    public Page<QualityControl> findByFilters(QualityControlFilter filter, Pageable pageable) {
        // Boş string'leri null'a çevir
        if (filter.getQcCode() != null && filter.getQcCode().trim().isEmpty()) filter.setQcCode(null);
        if (filter.getProductCode() != null && filter.getProductCode().trim().isEmpty()) filter.setProductCode(null);
        if (filter.getBatchNumber() != null && filter.getBatchNumber().trim().isEmpty()) filter.setBatchNumber(null);
        
        return qualityControlRepository.findByFilters(
            filter.getQcCode(),
            filter.getProductCode(),
            filter.getStatus(),
            filter.getInspector(),
            filter.getBatchNumber(),
            filter.getStartDate(),
            filter.getEndDate(),
            pageable
        );
    }
}