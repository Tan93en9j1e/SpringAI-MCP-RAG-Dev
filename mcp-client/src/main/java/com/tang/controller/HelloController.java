package com.tang.controller;

import com.tang.service.ChatService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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

    @GetMapping("chat/stream/response")
    public Flux<ChatResponse> chatStreamResponse(String msg, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        return chatService.streamResponse(msg);
    }

    @GetMapping("chat/stream/str")
    public Flux<String> chatStreamStr(String msg, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        return chatService.streamStr(msg);
    }
}
