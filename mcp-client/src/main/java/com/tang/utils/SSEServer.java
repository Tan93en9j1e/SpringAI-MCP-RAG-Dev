package com.tang.utils;

import com.tang.enums.SSEMsgType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.utils
 * ClassName: SSEServer
 * Author: tmj
 * Date: 2026/6/25 16:46
 * version: 1.0
 * Description:
 */
@Slf4j
public class SSEServer {

    // 存储所有的用户
    private static final Map<String, SseEmitter> sseClients = new ConcurrentHashMap<>();

    /**
     * TODO:连接SSE服务
     *
     * @param userId
     * @return org.springframework.web.servlet.mvc.method.annotation.SseEmitter
     * @author tmj
     * @since 2026/6/25 17:04
     **/
    public static SseEmitter connect(String userId) {

        //设置超时时间,0表示永不超时
        SseEmitter sseEmitter = new SseEmitter(0L);

        //回调函数
        sseEmitter.onTimeout(timeoutCallback(userId));
        sseEmitter.onCompletion(completionCallback(userId));
        sseEmitter.onError(errorCallback(userId));

        sseClients.put(userId, sseEmitter);

        log.info("Connected user: {}", userId);

        return sseEmitter;
    }

    /**
     * TODO:发送消息
     *
     * @param userId
     * @param message
     * @param msgType
     * @author tmj
     * @since 2026/6/25 17:34
     **/
    public static void sendMsg(String userId, String message, SSEMsgType msgType) {
        if (CollectionUtils.isEmpty(sseClients)) {
            return;
        }

        if (sseClients.containsKey(userId)) {
            SseEmitter sseEmitter = sseClients.get(userId);
            sendEmitterMessage(sseEmitter, userId, message, msgType);
        }
    }

    /**
     * TODO:消息群发
     *
     * @param message
     * @author tmj
     * @since 2026/6/25 20:31
     **/
    public static void sendMsgToAllUsers(String message) {
        if (CollectionUtils.isEmpty(sseClients)) {
            return;
        }
        sseClients.forEach((userId, sseEmitter) -> {
            sendEmitterMessage(sseEmitter, userId, message, SSEMsgType.MESSAGE);
        });
    }

    /**
     * TODO:发送消息
     *
     * @param sseEmitter
     * @param userId
     * @param message
     * @param msgType
     * @author tmj
     * @since 2026/6/25 17:34
     **/
    public static void sendEmitterMessage(SseEmitter sseEmitter, String userId, String message, SSEMsgType msgType) {
        try {
            SseEmitter.SseEventBuilder event = SseEmitter.event()
                    .data(message)
                    .id(userId)
                    .name(msgType.type);
            sseEmitter.send(msgType);
        } catch (IOException e) {
            log.error("User {} send error{}", userId, e.getMessage());
            remove(userId);
        }
    }


    /**
     * TODO:超时回调函数
     *
     * @param userId
     * @return java.lang.Runnable
     * @author tmj
     * @since 2026/6/25 17:04
     **/
    public static Runnable timeoutCallback(String userId) {
        return () -> {
            // 处理超时逻辑
            log.info("User {} timed out", userId);
            remove(userId);
        };
    }

    /**
     * TODO:完成回调函数
     *
     * @param userId
     * @return java.lang.Runnable
     * @author tmj
     * @since 2026/6/25 17:04
     **/
    public static Runnable completionCallback(String userId) {
        return () -> {
            // 处理完成逻辑
            log.info("User {} completed", userId);
            remove(userId);

        };
    }

    /**
     * TODO:错误回调函数
     *
     * @param userId
     * @return java.util.function.Consumer<java.lang.Throwable>
     * @author tmj
     * @since 2026/6/25 17:04
     **/
    public static Consumer<Throwable> errorCallback(String userId) {
        return Throwable -> {
            log.info("User {} error", userId, Throwable);
            // 处理错误逻辑
            remove(userId);

        };
    }

    /**
     * TODO:移除用户连接
     *
     * @param userId
     * @return void
     * @author tmj
     * @since 2026/6/25 17:04
     **/
    public static void remove(String userId) {
        // 移除用户
        sseClients.remove(userId);
        log.info("Removed user: {}", userId);
    }

}
