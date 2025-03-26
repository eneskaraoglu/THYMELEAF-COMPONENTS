package com.example.erp.config;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class DeviceDetectionInterceptor implements HandlerInterceptor {

    private static final String[] MOBILE_KEYWORDS = {
            "android", "iphone", "ipod", "windows phone", "blackberry", "mobile", "samsung"
    };
    
    private static final String[] TABLET_KEYWORDS = {
            "ipad", "tablet", "galaxy tab", "tab s"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent == null) {
            userAgent = "";
        } else {
            userAgent = userAgent.toLowerCase();
        }

        // Cihaz tipi tespiti
        boolean isMobile = Arrays.stream(MOBILE_KEYWORDS)
                .anyMatch(userAgent::contains);
        
        boolean isTablet = Arrays.stream(TABLET_KEYWORDS)
                .anyMatch(userAgent::contains);
                
        // Ekran boyutu için tahmini değerler (gerçek boyut client-side JS ile belirlenebilir)
        String deviceType = isTablet ? "tablet" : (isMobile ? "mobile" : "desktop");
        
        // View bileşenleri için özel parametreler
        request.setAttribute("deviceType", deviceType);
        request.setAttribute("isMobile", isMobile || isTablet);
        request.setAttribute("isTablet", isTablet);
        
        // Ekran genişliği için varsayılan değerler
        // (Gerçek değerler JavaScript ile alınıp sunucuya gönderilebilir)
        int defaultWidth = isMobile ? 375 : (isTablet ? 768 : 1200);
        request.setAttribute("screenWidth", defaultWidth);
        
        // Özel durumlar için viewport kontrolü
        boolean isNarrowLayout = defaultWidth < 768;
        boolean isWideLayout = defaultWidth >= 1200;
        request.setAttribute("isNarrowLayout", isNarrowLayout);
        request.setAttribute("isWideLayout", isWideLayout);
        
        return true;
    }
}

