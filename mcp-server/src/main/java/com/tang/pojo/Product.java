package com.tang.pojo;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.entity
 * ClassName: Product
 * Author: tmj
 * Date: 2026/7/1 14:35
 * version: 1.0
 * Description:
 */
@Data
@ToString
public class Product {

    private String productId;
    private String productName;
    private String brand;
    private String description;

    private Integer price;
    private Integer stock;
    private Integer status;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
