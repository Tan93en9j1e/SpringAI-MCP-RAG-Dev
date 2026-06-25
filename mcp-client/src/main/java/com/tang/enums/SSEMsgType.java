package com.tang.enums;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.enums
 * ClassName: SSEMsgType
 * Author: tmj
 * Date: 2026/6/25 17:16
 * version: 1.0
 * Description:发送SSE的消息类型
 */
public enum SSEMsgType {

    MESSAGE("message", "单词发送的普通类型消息"),
    ADD("add", "消息追加，适用于流式Stream推送"),
    FINISH("finish", "消息完成"),
    CUSTOM_EVENT("custom_event", "自定义事件"),
    DONE("done", "消息完成");

    public final String type;
    public final String value;

    SSEMsgType(String type, String value) {
        this.type = type;
        this.value = value;
    }
}
