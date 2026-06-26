package com.tang.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.bean
 * ClassName: SearXNGResponse
 * Author: tmj
 * Date: 2026/6/26 13:03
 * version: 1.0
 * Description:
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SearXNGResponse {

    private String query;

    private List<SearchResult> results;
}
