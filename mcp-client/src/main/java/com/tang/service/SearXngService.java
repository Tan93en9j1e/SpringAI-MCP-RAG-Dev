package com.tang.service;

import com.tang.bean.SearchResult;

import java.util.List;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.service
 * ClassName: SearXngService
 * Author: tmj
 * Date: 2026/6/26 12:41
 * version: 1.0
 * Description:
 */
public interface SearXngService {

    /**
     * TODO:调用SearXng搜索引擎
     * @author tmj
     * @since 2026/6/26 12:42
     * @param query
     * @return java.util.List<com.tang.bean.SearchResult>
     **/
    public List<SearchResult> search(String query);
}
