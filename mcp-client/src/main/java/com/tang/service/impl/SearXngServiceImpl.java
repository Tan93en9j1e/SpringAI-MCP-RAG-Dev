package com.tang.service.impl;

import com.tang.bean.SearchResult;
import com.tang.service.SearXngService;

import org.springframework.beans.factory.annotation.Value;
import java.util.List;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.service.impl
 * ClassName: SearXngServiceImpl
 * Author: tmj
 * Date: 2026/6/26 12:43
 * version: 1.0
 * Description:
 */
public class SearXngServiceImpl implements SearXngService {

    @Value("${internet.websearch.searxng.url}")
    private String SEARXNG_URL;

    @Value("${internet.websearch.searxng.counts}")
    private int COUNTS;

    @Override
    public List<SearchResult> search(String query) {
        return List.of();
    }
}
