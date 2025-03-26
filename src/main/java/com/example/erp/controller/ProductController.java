package com.example.erp.controller;

import com.example.erp.model.Product;
import com.example.erp.model.ProductFilter;
import com.example.erp.model.SearchCriteria;
import com.example.erp.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int[] PAGE_SIZES = {10, 25, 50};

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("searchCriterias")
    public SearchCriteria[] searchCriterias() {
        return SearchCriteria.values();
    }

    @ModelAttribute("pageSizes")
    public int[] pageSizes() {
        return PAGE_SIZES;
    }

    @GetMapping
    public String listProducts(
            @ModelAttribute ProductFilter filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        
        // Geçerli bir sayfa boyutu seçildiğinden emin olalım
        int pageSize = size;
        if (!Arrays.stream(PAGE_SIZES).anyMatch(s -> s == size)) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        
        Page<Product> productPage = productService.findByFilters(filter, PageRequest.of(page, pageSize));
        model.addAttribute("products", productPage);
        model.addAttribute("filter", filter);
        model.addAttribute("selectedPageSize", pageSize);
        
        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        
        return "products/pro-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/pro-form";
    }

    @PostMapping
    public String createProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "products/pro-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
} 