package com.example.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SupplierPortalController {

    @GetMapping("/supplier-portal")
    public String supplierPortal(Model model) {
        model.addAttribute("pageTitle", "Tedarikçi Portalı");
        return "supplier/portal";
    }
} 