package com.tang;

import com.tang.mcp.tool.DateTool;
import com.tang.mcp.tool.EmailTool;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang
 * ClassName: Application
 * Author: tmj
 * Date: 2026/6/25 14:16
 * version: 1.0
 * Description:
 */
@SpringBootApplication
@MapperScan("com.tang.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    /**
     * TODO:方法功能描述
     *
     * @param dateTool
     * @param emailTool
     * @return org.springframework.ai.tool.ToolCallbackProvider
     * @author tmj
     * @since 2026/6/30 20:13
     **/
    @Bean
    public ToolCallbackProvider registerMCPTools(DateTool dateTool, EmailTool emailTool) {
        return MethodToolCallbackProvider
                .builder()
                .toolObjects(dateTool)
                .build();
    }
}
