package com.tang.controller.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.controller.bean
 * ClassName: ChatEntity
 * Author: tmj
 * Date: 2026/6/25 20:40
 * version: 1.0
 * Description:
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChatEntity {
    private String currentUserName;
    private String message;
    private String botMsgId;
}
