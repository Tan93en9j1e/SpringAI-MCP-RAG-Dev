package com.tang.controller;

import com.tang.enums.SSEMsgType;
import com.tang.utils.SSEServer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.controller
 * ClassName: SSEController
 * Author: tmj
 * Date: 2026/6/25 14:18
 * version: 1.0
 * Description:
 */
@RestController
@RequestMapping("sse")
public class SSEController {

    /**
     * TODO:SSE连接
     *
     * @param userId
     * @return org.springframework.web.servlet.mvc.method.annotation.SseEmitter
     * @author tmj
     * @since 2026/6/25 17:33
     **/
    @GetMapping(path = "connect", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter connect(@RequestParam String userId) {
        return SSEServer.connect(userId);
    }

    /**
     * TODO:发送单个消息
     *
     * @param userId
     * @param message
     * @return java.lang.Object
     * @author tmj
     * @since 2026/6/25 17:31
     **/
    @GetMapping("sendMessage")
    public Object sendMessage(@RequestParam String userId, @RequestParam String message) {
        SSEServer.sendMsg(userId, message, SSEMsgType.MESSAGE);
        return "ok";
    }

    /**
     * TODO:发送单个消息-添加
     *
     * @param userId
     * @param message
     * @return java.lang.Object
     * @author tmj
     * @since 2026/6/25 20:34
     **/
    @GetMapping("sendMessageAdd")
    public Object sendMessageAdd(@RequestParam String userId, @RequestParam String message) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            SSEServer.sendMsg(userId, message, SSEMsgType.ADD);
        }
        return "ok";
    }

    /**
     * TODO:消息群发
     *
     * @param message
     * @return java.lang.Object
     * @author tmj
     * @since 2026/6/25 20:31
     **/
    @GetMapping("sendMessageAll")
    public Object sendMessageAll(@RequestParam String message) {
        SSEServer.sendMsgToAllUsers(message);
        return "ok";
    }
}
