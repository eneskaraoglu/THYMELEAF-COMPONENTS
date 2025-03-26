package com.example.erp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    private final DeviceDetectionInterceptor deviceDetectionInterceptor;
    
    public WebConfig(DeviceDetectionInterceptor deviceDetectionInterceptor) {
        this.deviceDetectionInterceptor = deviceDetectionInterceptor;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(deviceDetectionInterceptor);
    }
}