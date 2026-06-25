package com.tang.controller;

import com.tang.controller.bean.ChatEntity;
import com.tang.service.ChatService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.controller
 * ClassName: ChatController
 * Author: tmj
 * Date: 2026/6/25 14:18
 * version: 1.0
 * Description:
 */
@RestController
@RequestMapping("chat")

public class ChatController {

    @Resource
    private ChatService chatService;


    @PostMapping("doChat")
    public void chat(@RequestBody ChatEntity chatEntity) {
        chatService.doChat(chatEntity);
    }
}
