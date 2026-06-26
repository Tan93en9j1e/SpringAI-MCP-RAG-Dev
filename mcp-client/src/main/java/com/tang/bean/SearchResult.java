package com.tang.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.bean
 * ClassName: SearchResult
 * Author: tmj
 * Date: 2026/6/26 12:39
 * version: 1.0
 * Description:
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SearchResult {

    private String title;
    private String content;
    private String url;
    private double score;
}
