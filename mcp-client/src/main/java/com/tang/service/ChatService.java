package com.tang.service;

import org.springframework.ai.chat.model.ChatResponse;
import reactor.core.publisher.Flux;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.service
 * ClassName: ChatService
 * Author: tmj
 * Date: 2026/6/25 14:38
 * version: 1.0
 * Description:
 */
public interface ChatService {

    /**
     * TODO:测试大模型交互聊天
     *
     * @param prompt
     * @return java.lang.String
     * @author tmj
     * @since 2026/6/25 14:49
     **/
    public String chatTest(String prompt);

    /**
     * TODO:流式响应
     *
     * @param prompt
     * @return reactor.core.publisher.Flux<org.springframework.ai.chat.model.ChatResponse>
     * @author tmj
     * @since 2026/6/25 14:51
     **/
    public Flux<ChatResponse> streamResponse(String prompt);

    /**
     * TODO:String流式响应
     *
     * @param prompt
     * @return reactor.core.publisher.Flux<java.lang.String>
     * @author tmj
     * @since 2026/6/25 14:55
     **/
    public Flux<String> streamStr(String prompt);
}
