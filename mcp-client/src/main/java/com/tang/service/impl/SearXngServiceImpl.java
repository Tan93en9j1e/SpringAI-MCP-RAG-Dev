package com.tang.service.impl;

import com.tang.bean.SearchResult;
import com.tang.service.SearXngService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
@Service
@RequiredArgsConstructor
@Slf4j
public class SearXngServiceImpl implements SearXngService {

    @Value("${internet.websearch.searxng.url}")
    private String SEARXNG_URL;

    @Value("${internet.websearch.searxng.counts}")
    private int COUNTS;

    private final OkHttpClient okHttpClient;

    @Override
    public List<SearchResult> search(String query) {

        HttpUrl url = HttpUrl.get(SEARXNG_URL)
                .newBuilder()
                .addQueryParameter("q", query)
                .addQueryParameter("format", "json")
                .build();

        log.info("搜索的url: {}", url.url());

//        okHttpClient.newCall()

        return List.of();
    }
}
