package com.tang.mcp.tool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.mcp.tool
 * ClassName: DateTool
 * Author: tmj
 * Date: 2026/6/26 22:07
 * version: 1.0
 * Description:
 */
@Component
@Slf4j
public class DateTool {

    @Tool(description = "获取当前时间")
    public String getCurrentTime() {
        String currentTime = String.format("当前时间是%s",
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return currentTime;
    }
}
