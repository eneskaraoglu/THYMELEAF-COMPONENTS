package com.example.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NavigationController {

    // Ana sayfa - Navigasyon sayfası
    @GetMapping("/")
    public String home(Model model) {
        // Menü öğeleri için model hazırlama
        model.addAttribute("pageTitle", "Ana Sayfa");
        return "index";
    }
    
    // Responsive tasarım sayfası
    @GetMapping("/responsive")
    public String responsive(Model model) {
        // Responsive görünüm için gerekli verileri oluştur
        List<ComponentItem> items = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            boolean isSpecial = i % 4 == 0;
            items.add(new ComponentItem(
                    "Bileşen " + i, 
                    "Bu bileşen responsive olarak yerleşecek. " + 
                    (isSpecial ? "Bu özel bir bileşendir." : "Standart içerik."), 
                    isSpecial));
        }
        
        // Model'e bileşenleri ekle
        model.addAttribute("items", items);
        model.addAttribute("pageTitle", "Responsive Görünüm");
        return "responsive";
    }
    
    // Liste görünümü sayfası
    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "grid") String view) {
        // Liste görünümü için veri oluştur
        List<ListItem> listItems = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            listItems.add(new ListItem(
                    i,
                    "Öğe " + i,
                    "Bu bir liste öğesidir. Her öğe ID, başlık ve açıklama içerir.",
                    "Kategori " + ((i % 4) + 1),
                    i % 2 == 0 // Alternatif satırları işaretlemek için
            ));
        }
        
        // Model'e liste verilerini ekle
        model.addAttribute("listItems", listItems);
        model.addAttribute("viewType", view); // grid veya list görünümü için
        model.addAttribute("pageTitle", "Liste Görünümü");
        return "list";
    }
    
    // İç sınıf - bileşen veri modeli
    public static class ComponentItem {
        private String title;
        private String description;
        private boolean special;
        
        public ComponentItem(String title, String description, boolean special) {
            this.title = title;
            this.description = description;
            this.special = special;
        }
        
        // Getter ve setter'lar
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public boolean isSpecial() { return special; }
        public void setSpecial(boolean special) { this.special = special; }
    }
    
    // İç sınıf - liste öğesi veri modeli
    public static class ListItem {
        private int id;
        private String title;
        private String description;
        private String category;
        private boolean highlighted;
        
        public ListItem(int id, String title, String description, String category, boolean highlighted) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.category = category;
            this.highlighted = highlighted;
        }
        
        // Getter ve setter'lar
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        public boolean isHighlighted() { return highlighted; }
        public void setHighlighted(boolean highlighted) { this.highlighted = highlighted; }
    }
}