package com.tang.service.impl;

import com.tang.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.service.impl
 * ClassName: ChatServiceImpl
 * Author: tmj
 * Date: 2026/6/25 14:38
 * version: 1.0
 * Description:
 */
@Service
public class ChatServiceImpl implements ChatService {

    private ChatClient chatClient;

    private String systemPrompt ="You are a helpful assistant.";

    /**
     * TODO:构造器注入，自动配置
     *
     * @param clientBuilder
     * @return null
     * @author tmj
     * @since 2026/6/25 14:42
     **/
    public ChatServiceImpl(ChatClient.Builder clientBuilder) {
        this.chatClient = clientBuilder
//                .defaultSystem(systemPrompt)
                .build();
    }

    @Override
    public String chatTest(String prompt) {
        return chatClient.prompt(prompt).call().content();
    }
}
