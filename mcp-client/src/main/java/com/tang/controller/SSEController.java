package com.tang.controller;

import com.tang.utils.SSEServer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.print.attribute.standard.Media;

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
     * TODO:前端发送请求，连接SSE服务
     *
     * @return java.lang.String
     * @author tmj
     * @since 2026/6/25 17:09
     **/
    @GetMapping(path = "connect", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter connect(@RequestParam String userId) {
        return SSEServer.connect(userId);
    }
}
