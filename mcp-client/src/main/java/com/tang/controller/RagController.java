package com.tang.controller;

import com.tang.service.DocumentService;
import com.tang.utils.LeeResult;
import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.controller
 * ClassName: RagController
 * Author: tmj
 * Date: 2026/6/25 21:20
 * version: 1.0
 * Description:
 */
@RestController
@RequestMapping("rag")
public class RagController {

    @Resource
    private DocumentService documentService;

    @PostMapping("uploadRagDoc")
    public LeeResult uploadRagDoc(@RequestParam("file")MultipartFile file) {
        List<Document> documentList = documentService.loadText(file.getResource(), file.getOriginalFilename());
        return LeeResult.ok(documentList);
    }
}
