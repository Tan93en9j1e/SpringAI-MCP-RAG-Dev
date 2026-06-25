package com.tang.service.impl;

import com.tang.service.DocumentService;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.service.impl
 * ClassName: DocumentServiceImpl
 * Author: tmj
 * Date: 2026/6/25 21:40
 * version: 1.0
 * Description:
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    @Override
    public List<Document> loadText(Resource resource, String fileName) {

        //加载读取文档
        TextReader textReader =new TextReader(resource);
        textReader.getCustomMetadata().put("fileName", fileName);
        List<Document> documentList = textReader.get();

        System.out.println("documentList" + documentList);

        return documentList;
    }
}
