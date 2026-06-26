package com.tang.controller;

import com.tang.bean.ChatEntity;
import com.tang.service.ChatService;
import com.tang.service.SearXngService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
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

    @Resource
    private ChatService chatService;

    @GetMapping("test")
    public Object test(@RequestParam("query") String query) {
        return searXngService.search(query);
    }

    @PostMapping("search")
    public void search(@RequestBody ChatEntity chatEntity, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        chatService.doInternetSearch(chatEntity);
    }
}
