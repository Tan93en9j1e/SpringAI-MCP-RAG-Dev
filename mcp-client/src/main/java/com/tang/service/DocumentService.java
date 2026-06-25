package com.tang.service;

import org.springframework.ai.document.Document;
import org.springframework.core.io.Resource;

import java.util.List;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.service
 * ClassName: DocumentService
 * Author: tmj
 * Date: 2026/6/25 21:40
 * version: 1.0
 * Description:
 */
public interface DocumentService {

    /**
     * TODO:加载文档并读取数据到知识库
     * @author tmj
     * @since 2026/6/25 21:43
     * @param resource
     * @param fileName
     **/
    public List<Document> loadText (Resource resource, String fileName);

    /**
     * TODO:根据提问从知识库中查询相关文档
     * @author tmj
     * @since 2026/6/25 22:07
     * @param question
     * @return java.util.List<org.springframework.ai.document.Document>
     **/
    public List<Document> doSearch (String question);
}
