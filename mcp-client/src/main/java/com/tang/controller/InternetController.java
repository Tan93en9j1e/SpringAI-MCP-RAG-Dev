package com.tang.controller;

import com.tang.service.SearXngService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.controller
 * ClassName: InternetController
 * Author: tmj
 * Date: 2026/6/26 12:46
 * version: 1.0
 * Description:
 */
@RestController
@RequestMapping("internet")
public class InternetController {

    @Resource
    private SearXngService searXngService;

    @GetMapping("test")
    public Object test(@RequestParam("query") String query) {
        return searXngService.search(query);
    }
}
