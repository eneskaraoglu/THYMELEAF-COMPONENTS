package com.example.erp.controller;

import com.example.erp.model.RawMaterial;
import com.example.erp.model.RawMaterialFilter;
import com.example.erp.service.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/raw-materials")
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;

    @Autowired
    public RawMaterialController(RawMaterialService rawMaterialService) {
        this.rawMaterialService = rawMaterialService;
    }

    @GetMapping
    public String listRawMaterials(Model model) {
        model.addAttribute("rawMaterials", rawMaterialService.findAll());
        model.addAttribute("filter", new RawMaterialFilter());
        model.addAttribute("categories", rawMaterialService.findAllCategories());
        model.addAttribute("suppliers", rawMaterialService.findAllSuppliers());
        model.addAttribute("pageTitle", "Hammadde Stok Yönetimi");
        return "raw-materials/raw-material-list";
    }

    @PostMapping("/filter")
    public String filterRawMaterials(@ModelAttribute RawMaterialFilter filter, Model model) {
        model.addAttribute("rawMaterials", rawMaterialService.findByFilter(filter));
        model.addAttribute("filter", filter);
        model.addAttribute("categories", rawMaterialService.findAllCategories());
        model.addAttribute("suppliers", rawMaterialService.findAllSuppliers());
        model.addAttribute("pageTitle", "Hammadde Stok Yönetimi - Filtrelenmiş");
        return "raw-materials/raw-material-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("rawMaterial", new RawMaterial());
        model.addAttribute("pageTitle", "Yeni Hammadde Ekle");
        return "raw-materials/raw-material-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        RawMaterial rawMaterial = rawMaterialService.findById(id);
        model.addAttribute("rawMaterial", rawMaterial);
        model.addAttribute("pageTitle", "Hammadde Düzenle: " + rawMaterial.getName());
        return "raw-materials/raw-material-form";
    }

    @PostMapping("/save")
    public String saveRawMaterial(@ModelAttribute RawMaterial rawMaterial) {
        // Ensure numbers are not null
        if (rawMaterial.getQuantity() == null) {
            rawMaterial.setQuantity(BigDecimal.ZERO);
        }
        if (rawMaterial.getMinimumStock() == null) {
            rawMaterial.setMinimumStock(BigDecimal.ZERO);
        }
        if (rawMaterial.getReorderPoint() == null) {
            rawMaterial.setReorderPoint(BigDecimal.ZERO);
        }
        
        rawMaterialService.save(rawMaterial);
        return "redirect:/raw-materials";
    }

    @GetMapping("/delete/{id}")
    public String deleteRawMaterial(@PathVariable Long id) {
        rawMaterialService.deleteById(id);
        return "redirect:/raw-materials";
    }

    @GetMapping("/low-stock")
    public String showLowStockMaterials(Model model) {
        List<RawMaterial> lowStockMaterials = rawMaterialService.findMaterialsBelowMinimumStock();
        model.addAttribute("rawMaterials", lowStockMaterials);
        model.addAttribute("pageTitle", "Düşük Stok Hammaddeler");
        return "raw-materials/raw-material-list";
    }

    @GetMapping("/needs-reorder")
    public String showMaterialsNeedingReorder(Model model) {
        List<RawMaterial> reorderMaterials = rawMaterialService.findMaterialsNeedingReorder();
        model.addAttribute("rawMaterials", reorderMaterials);
        model.addAttribute("pageTitle", "Sipariş Gerektiren Hammaddeler");
        return "raw-materials/raw-material-list";
    }
}