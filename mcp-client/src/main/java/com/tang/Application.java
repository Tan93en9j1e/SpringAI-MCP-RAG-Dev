package com.tang;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
public class Application {
    public static void main(String[] args) {

        // 加载env文件
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        //加载环境变量
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        SpringApplication.run(Application.class);
    }
}
