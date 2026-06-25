package com.tang;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang
 * ClassName: CorsConfig
 * Author: tmj
 * Date: 2026/6/25 20:17
 * version: 1.0
 * Description:
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${website.domain}")
    private String domain;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(domain)
//                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(60 * 60);
    }
}
