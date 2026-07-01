package com.tang.mcp.tool;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tang.mapper.ProductMapper;
import com.tang.pojo.Product;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.mcp.tool
 * ClassName: ProductTool
 * Author: tmj
 * Date: 2026/7/1 14:48
 * version: 1.0
 * Description:
 */
@Component
@Slf4j
public class ProductTool {

    @Resource
    private ProductMapper productMapper;

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateProductRequest {
        @ToolParam(description = "商品名称")
        private String productName;
        @ToolParam(description = "商品品牌")
        private String brand;
        @ToolParam(description = "商品描述(可以为空)")
        private String description;

        @ToolParam(description = "商品价格(单位：元)")
        private Integer price;
        @ToolParam(description = "商品库存的数量(单位：件)")
        private Integer stock;
        @ToolParam(description = "商品状态，0-下架，1-上架，2-预售")
        private Integer status;
    }

    @Tool(description = "创建/新增商品信息记录")
    public String createCurrentProduct(CreateProductRequest createProductRequest) {

        log.info(String.format("| 参数: %s", createProductRequest.toString()));

        Product product = new Product();
        BeanUtils.copyProperties(createProductRequest, product);

        //生成12为随机数字
        product.setProductId(RandomStringUtils.randomNumeric(12));

        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());

        productMapper.insert(product);

        return "商品信息创建成功";
    }

    @Transactional
    @Tool(description = "根据商品id删除商品记录")
    public String deleteProductById(String productId) {

        log.info(String.format("| 参数: %s", productId));

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);

        productMapper.delete(queryWrapper);

        return "商品信息删除成功";
    }
}
