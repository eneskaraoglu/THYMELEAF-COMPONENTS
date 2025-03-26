package com.example.erp.service;

import com.example.erp.model.Inspector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectorRepository extends JpaRepository<Inspector, Long> {
}