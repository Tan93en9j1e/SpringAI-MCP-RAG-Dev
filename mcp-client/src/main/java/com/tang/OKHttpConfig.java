package com.tang;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang
 * ClassName: OKHttpConfig
 * Author: tmj
 * Date: 2026/6/26 12:50
 * version: 1.0
 * Description:
 */
@Configuration
public class OKHttpConfig implements WebMvcConfigurer {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }
}
