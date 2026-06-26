package com.tang.service.impl;

import cn.hutool.json.JSONUtil;
import com.tang.bean.SearXNGResponse;
import com.tang.bean.SearchResult;
import com.tang.service.SearXngService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
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

        // 构建搜索的url
        HttpUrl url = HttpUrl.get(SEARXNG_URL)
                .newBuilder()
                .addQueryParameter("q", query)
                .addQueryParameter("format", "json")
                .build();

        log.info("搜索的url: {}", url.url());

        // 创建请求对象
        Request request = new Request.Builder()
                .url(url)
                .build();
        // 执行请求
        try (Response response = okHttpClient.newCall(request).execute()) {

            // 判断响应是否成功
            if (!response.isSuccessful()) throw new RuntimeException("请求失败:HTTP " + response.code());

            // 获取响应体
            if (response.body() != null) {
                String responseBody = response.body().string();
                SearXNGResponse searXNGResponse = JSONUtil.toBean(responseBody, SearXNGResponse.class);
                return dealResult(searXNGResponse.getResults());
            }
            log.error("搜索失败: {}", response.message());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Collections.emptyList();
    }

    /**
     * TODO:处理结构集，截取限制的个数
     *
     * @param results
     * @return java.util.List<com.tang.bean.SearchResult>
     * @author tmj
     * @since 2026/6/26 13:19
     **/
    private List<SearchResult> dealResult(List<SearchResult> results) {

        return results.subList(0, Math.min(COUNTS, results.size()))
                .parallelStream()
                .sorted(Comparator.comparingDouble(SearchResult::getScore).reversed())
                .limit(COUNTS).toList();
    }
}
