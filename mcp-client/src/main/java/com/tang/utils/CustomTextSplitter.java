package com.tang.utils;

import org.springframework.ai.transformer.splitter.TextSplitter;

import java.util.List;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.utils
 * ClassName: CustomTextSplitter
 * Author: tmj
 * Date: 2026/6/25 21:55
 * version: 1.0
 * Description:
 */
public class CustomTextSplitter extends TextSplitter {

    @Override
    protected List<String> splitText(String text) {
        return List.of(split(text));
    }

    public String[] split(String text){
        return text.split("\\s*\\R\\s*\\R\\s*");
    }
}
