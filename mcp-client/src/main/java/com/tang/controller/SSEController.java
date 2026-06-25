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
     * @author tmj
     * @since 2026/6/25 17:33
     * @param userId
     * @return org.springframework.web.servlet.mvc.method.annotation.SseEmitter
     **/
    @GetMapping(path = "connect", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter connect(@RequestParam String userId) {
        return SSEServer.connect(userId);
    }

    /**
     * TODO:SSE 发送单个消息
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
}
