package com.example.erp.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.erp.model.Inspector;
import com.example.erp.model.Product;
import com.example.erp.model.QualityControl;
import com.example.erp.model.QualityControlFilter;
import com.example.erp.model.QualityControlStatus;
import com.example.erp.service.InspectorService;
import com.example.erp.service.ProductService;
import com.example.erp.service.QualityControlService;

@Controller
@RequestMapping("/quality-controls")
public class QualityControlController {
    
    private final QualityControlService qualityControlService;
    private final ProductService productService;
    private final InspectorService inspectorService;
    
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int[] PAGE_SIZES = {10, 25, 50};

    public QualityControlController(
            QualityControlService qualityControlService,
            ProductService productService,
            InspectorService inspectorService) {
        this.qualityControlService = qualityControlService;
        this.productService = productService;
        this.inspectorService = inspectorService;
    }

    @ModelAttribute("statuses")
    public QualityControlStatus[] statuses() {
        return QualityControlStatus.values();
    }

    @ModelAttribute("inspectors")
    public List<Inspector> inspectors() {
        return inspectorService.findAll();
    }
    
    @ModelAttribute("products")
    public List<Product> products() {
        return productService.findAll();
    }

    @ModelAttribute("pageSizes")
    public int[] pageSizes() {
        return PAGE_SIZES;
    }

    @GetMapping
    public String listQualityControls(
            @ModelAttribute QualityControlFilter filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        
        // Geçerli bir sayfa boyutu seçildiğinden emin olalım
        int pageSize = size;
        if (!Arrays.stream(PAGE_SIZES).anyMatch(s -> s == size)) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        
        Page<QualityControl> qualityControlPage = qualityControlService.findByFilters(
                filter, PageRequest.of(page, pageSize));
        
        model.addAttribute("qualityControls", qualityControlPage);
        model.addAttribute("filter", filter);
        model.addAttribute("selectedPageSize", pageSize);
        
        int totalPages = qualityControlPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        
        return "quality-controls/quality-control-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        QualityControl qualityControl = new QualityControl();
        qualityControl.setInspectionDate(LocalDate.now());
        qualityControl.setStatus(QualityControlStatus.PENDING);
        
        model.addAttribute("qualityControl", qualityControl);
        return "quality-controls/quality-control-form";
    }

    @PostMapping
    public String createQualityControl(@ModelAttribute QualityControl qualityControl) {
        // Ürün ve kontrolör detaylarını al
        if (qualityControl.getProductId() != null) {
            Product product = productService.findById(qualityControl.getProductId());
            qualityControl.setProductCode(product.getCode());
            qualityControl.setProductName(product.getName());
            qualityControl.setManufacturingDate(product.getManufacturingDate());
        }
        
        if (qualityControl.getInspectorId() != null) {
            Inspector inspector = inspectorService.findById(qualityControl.getInspectorId());
            qualityControl.setInspectorName(inspector.getName());
        }
        
        // Kriterleri kalite kontrole bağla
        if (qualityControl.getCriteriaList() != null) {
            qualityControl.getCriteriaList().forEach(criteria -> criteria.setQualityControl(qualityControl));
        }
        
        qualityControlService.save(qualityControl);
        return "redirect:/quality-controls";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("qualityControl", qualityControlService.findById(id));
        return "quality-controls/quality-control-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteQualityControl(@PathVariable Long id) {
        qualityControlService.deleteById(id);
        return "redirect:/quality-controls";
    }
}