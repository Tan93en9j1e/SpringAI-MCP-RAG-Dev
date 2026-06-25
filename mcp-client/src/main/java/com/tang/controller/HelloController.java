package com.tang.controller;

import com.tang.service.ChatService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.controller
 * ClassName: HelloController
 * Author: tmj
 * Date: 2026/6/25 14:18
 * version: 1.0
 * Description:
 */
@RestController
@RequestMapping("hello")

public class HelloController {

    @Resource
    private ChatService chatService;

    @GetMapping("world" )
    public String world(){
        return "hello world";
    }

    @GetMapping("chat")
    public String chat(String msg){
        return chatService.chatTest(msg);
    }
}
