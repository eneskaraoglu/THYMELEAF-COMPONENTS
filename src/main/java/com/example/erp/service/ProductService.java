package com.example.erp.service;

import com.example.erp.model.Product;
import com.example.erp.model.ProductFilter;
import com.example.erp.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> findByFilters(ProductFilter filter, Pageable pageable) {
        if (filter.getCode() != null && filter.getCode().trim().isEmpty()) filter.setCode(null);
        if (filter.getName() != null && filter.getName().trim().isEmpty()) filter.setName(null);
        if (filter.getDescription() != null && filter.getDescription().trim().isEmpty()) filter.setDescription(null);
        
        return productRepository.findByFilters(
            filter.getCode(),
            filter.getName(),
            filter.getNameSearchCriteria().name(),
            filter.getPrice(),
            filter.getPriceSearchCriteria().name(),
            filter.getStock(),
            filter.getStockSearchCriteria().name(),
            filter.getDescription(),
            filter.getStartDate(),
            filter.getEndDate(),
            pageable
        );
    }
} 