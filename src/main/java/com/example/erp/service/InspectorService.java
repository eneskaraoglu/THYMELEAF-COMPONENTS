package com.example.erp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.erp.model.Inspector;

@Service
public class InspectorService {
    
    private final InspectorRepository inspectorRepository;
    
    public InspectorService(InspectorRepository inspectorRepository) {
        this.inspectorRepository = inspectorRepository;
    }
    
    public List<Inspector> findAll() {
        return inspectorRepository.findAll();
    }
    
    public Inspector findById(Long id) {
        return inspectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kontrolör bulunamadı"));
    }
    
    public Inspector save(Inspector inspector) {
        return inspectorRepository.save(inspector);
    }
    
    public void deleteById(Long id) {
        inspectorRepository.deleteById(id);
    }
}