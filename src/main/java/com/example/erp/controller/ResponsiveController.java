package com.example.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ResponsiveController {

    @GetMapping("/responsive/responsive")
    public String home(Model model) {
        // Örnek veri modelleri
        List<ComponentItem> normalItems = new ArrayList<>();
        normalItems.add(new ComponentItem("Bileşen 1", "Bu standart bir bileşendir ve otomatik yerleşecek.", false));
        normalItems.add(new ComponentItem("Bileşen 2", "Bu da standart bir bileşendir.", false));
        normalItems.add(new ComponentItem("Özel Bileşen", "Bu özel bir bileşendir ve farklı görünecek.", true));
        normalItems.add(new ComponentItem("Bileşen 3", "Başka bir standart bileşen.", false));
        normalItems.add(new ComponentItem("Bileşen 4", "Son standart bileşen.", false));
        
        // Öncelikli bileşen
        ComponentItem priorityItem = new ComponentItem("Öncelikli Bileşen", 
                "Bu çok önemli bir bileşendir ve daha fazla alan kaplar.", false);
        
        // Masonry bileşenleri
        List<ComponentItem> masonryItems = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            boolean isSpecial = i % 5 == 0;
            masonryItems.add(new ComponentItem(
                    "Masonry Bileşen " + i, 
                    "Bu masonry bileşeni otomatik yerleşecek. Bu bileşenin içeriği " + 
                    (i % 3 == 0 ? "daha uzun ve farklı yükseklikte" : "kısa"), 
                    isSpecial));
        }
        
        // Bootstrap bileşenleri
        List<ComponentItem> bootstrapItems = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            boolean isSpecial = i % 4 == 0;
            bootstrapItems.add(new ComponentItem(
                    "Bootstrap Bileşen " + i, 
                    "Bootstrap grid kullanarak otomatik düzenlenen bileşen. " + 
                    (isSpecial ? "Bu özel bir bileşendir." : "Standart içerik."), 
                    isSpecial));
        }
        
        // Model'e bileşenleri ekle
        model.addAttribute("normalItems", normalItems);
        model.addAttribute("priorityItem", priorityItem);
        model.addAttribute("masonryItems", masonryItems);
        model.addAttribute("bootstrapItems", bootstrapItems);
        
        return "responsive/responsive";
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
}