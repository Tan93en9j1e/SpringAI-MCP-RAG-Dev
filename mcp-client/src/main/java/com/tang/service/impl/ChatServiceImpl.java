package com.tang.service.impl;

import cn.hutool.json.JSONUtil;
import com.tang.bean.ChatEntity;
import com.tang.bean.ChatResponseEntity;
import com.tang.bean.SearchResult;
import com.tang.enums.SSEMsgType;
import com.tang.service.ChatService;
import com.tang.service.SearXngService;
import com.tang.utils.SSEServer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

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
@Slf4j
public class ChatServiceImpl implements ChatService {

    private ChatClient chatClient;

    @Resource
    private SearXngService searXngService;

    private String systemPrompt = "You are a helpful assistant.";

    //提示词三大模型
    //system
    //user
    //assistant

    public ChatServiceImpl(ChatClient.Builder clientBuilder) {
        this.chatClient = clientBuilder
//                .defaultSystem(systemPrompt)
                .build();
    }

    @Override
    public String chatTest(String prompt) {
//        try {
//            Thread.sleep(6000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return chatClient.prompt(prompt).call().content();
    }


    @Override
    public Flux<ChatResponse> streamResponse(String prompt) {
//        try {
//            Thread.sleep(6000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return chatClient.prompt(prompt).stream().chatResponse();
    }

    @Override
    public Flux<String> streamStr(String prompt) {
//        try {
//            Thread.sleep(6000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return chatClient.prompt(prompt).stream().content();
    }

    @Override
    public void doChat(ChatEntity chatEntity) {

        String userId = chatEntity.getCurrentUserName();
        String prompt = chatEntity.getMessage();
        String botMsgId = chatEntity.getBotMsgId();

        Flux<String> stringFlux = chatClient.prompt(prompt).stream().content();

        List<String> list = stringFlux.toStream().map(chatResponse -> {
            String content = chatResponse.toString();
            SSEServer.sendMsg(userId, content, SSEMsgType.ADD);
            log.info("content:{}", content);
            return content;
        }).collect(Collectors.toList());

        String fullContent = list.stream().collect(Collectors.joining());

        ChatResponseEntity chatResponseEntity = new ChatResponseEntity(fullContent, botMsgId);

        SSEServer.sendMsg(userId, JSONUtil.toJsonStr(chatResponseEntity), SSEMsgType.FINISH);
    }

    //Dify 智能体引擎构建平台

    private static final String ragPROMPT = """
            基于上下文的知识库内容回答问题:
            【上下文】
            {context}
            
            【问题】
            {question}
            
            【输出】
            如果没有查到,请回复：不知道。
            如果查到请回复具体的内容.不相关的近似内容不必提到。
            """;

    @Override
    public void doChatRagSearch(ChatEntity chatEntity, List<Document> ragContext) {

        String userId = chatEntity.getCurrentUserName();
        String question = chatEntity.getMessage();
        String botMsgId = chatEntity.getBotMsgId();

        // 构建上下文
        String context = null;
        if (ragContext != null && ragContext.size() > 0) {
            context = ragContext.stream()
                    .map(Document::getText)
                    .collect(Collectors.joining("\n"));
        }

        // 构建提示词
        Prompt prompt = new Prompt(ragPROMPT
                .replace("{context}", context)
                .replace("{question}", question));

//        System.out.println(prompt.toString());

        Flux<String> stringFlux = chatClient.prompt(prompt).stream().content();

        List<String> list = stringFlux.toStream().map(chatResponse -> {
            String content = chatResponse.toString();
            SSEServer.sendMsg(userId, content, SSEMsgType.ADD);
            log.info("content:{}", content);
            return content;
        }).collect(Collectors.toList());

        String fullContent = list.stream().collect(Collectors.joining());

        ChatResponseEntity chatResponseEntity = new ChatResponseEntity(fullContent, botMsgId);

        SSEServer.sendMsg(userId, JSONUtil.toJsonStr(chatResponseEntity), SSEMsgType.FINISH);

    }

    private static final String searXngPROMPT = """
            你是一个互联网搜索大师，请基于以下互联网返回的结果作为上下文，根据你的理解结合用户的提问，综合后，生成并且输出专业的回答：
            【上下文】
            {context}
            
            【问题】
            {question}
            
            【输出】
            如果没有查到,请回复：不知道。
            如果查到请回复具体的内容。
            """;

    @Override
    public void doInternetSearch(ChatEntity chatEntity) {
        String userId = chatEntity.getCurrentUserName();
        String question = chatEntity.getMessage();
        String botMsgId = chatEntity.getBotMsgId();

        List<SearchResult> searchResults = searXngService.search(question);

        String finalPrompt = buildSearXngPrompt(question, searchResults);

        // 构建提示词
        Prompt prompt = new Prompt(finalPrompt);

        Flux<String> stringFlux = chatClient.prompt(prompt).stream().content();

        List<String> list = stringFlux.toStream().map(chatResponse -> {
            String content = chatResponse.toString();
            SSEServer.sendMsg(userId, content, SSEMsgType.ADD);
            log.info("content:{}", content);
            return content;
        }).collect(Collectors.toList());

        String fullContent = list.stream().collect(Collectors.joining());

        ChatResponseEntity chatResponseEntity = new ChatResponseEntity(fullContent, botMsgId);

        SSEServer.sendMsg(userId, JSONUtil.toJsonStr(chatResponseEntity), SSEMsgType.FINISH);

    }

    private static String buildSearXngPrompt(String question, List<SearchResult> searchResults) {

        StringBuilder context = new StringBuilder();
        searchResults.forEach(searchResult -> {
            context.append(
                    String.format("<context>\n[来源] %s \n [摘要] %s \n<context>\n",
                            searchResult.getUrl(),
                            searchResult.getContent()));
        });
        return searXngPROMPT.replace("{context}", context)
                .replace("{question}", question);
    }
}
