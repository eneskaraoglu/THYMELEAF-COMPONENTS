package com.example.erp.repository;

import com.example.erp.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query("SELECT p FROM Product p WHERE " +
           "(:code IS NULL OR p.code LIKE %:code%) AND " +
           "(:name IS NULL OR " +
           "   ((:nameSearchCriteria = 'EQUALS' AND p.name = :name) OR " +
           "    (:nameSearchCriteria = 'CONTAINS' AND p.name LIKE %:name%) OR " +
           "    (:nameSearchCriteria = 'GREATER_THAN' AND p.name > :name) OR " +
           "    (:nameSearchCriteria = 'LESS_THAN' AND p.name < :name))) AND " +
           "(:price IS NULL OR " +
           "   ((:priceSearchCriteria = 'EQUALS' AND p.price = :price) OR " +
           "    (:priceSearchCriteria = 'GREATER_THAN' AND p.price > :price) OR " +
           "    (:priceSearchCriteria = 'LESS_THAN' AND p.price < :price))) AND " +
           "(:stock IS NULL OR " +
           "   ((:stockSearchCriteria = 'EQUALS' AND p.stock = :stock) OR " +
           "    (:stockSearchCriteria = 'GREATER_THAN' AND p.stock > :stock) OR " +
           "    (:stockSearchCriteria = 'LESS_THAN' AND p.stock < :stock))) AND " +
           "(:description IS NULL OR p.description LIKE %:description%) AND " +
           "(:startDate IS NULL OR p.manufacturingDate >= :startDate) AND " +
           "(:endDate IS NULL OR p.manufacturingDate <= :endDate)")
    Page<Product> findByFilters(@Param("code") String code,
                               @Param("name") String name,
                               @Param("nameSearchCriteria") String nameSearchCriteria,
                               @Param("price") Double price,
                               @Param("priceSearchCriteria") String priceSearchCriteria,
                               @Param("stock") Integer stock,
                               @Param("stockSearchCriteria") String stockSearchCriteria,
                               @Param("description") String description,
                               @Param("startDate") LocalDate startDate,
                               @Param("endDate") LocalDate endDate,
                               Pageable pageable);
} 