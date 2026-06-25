package com.tang.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.controller.bean
 * ClassName: ChatResponseEntity
 * Author: tmj
 * Date: 2026/6/25 20:40
 * version: 1.0
 * Description:
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponseEntity {

    private String message;
    private String botMsgId;
}
