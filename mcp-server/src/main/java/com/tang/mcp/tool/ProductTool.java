package com.tang.mcp.tool;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tang.enums.ListSortEnum;
import com.tang.enums.PriceCompareEnum;
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
import java.util.List;

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

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QueryProductRequest {

        //required = TRUE ，默认自动填充。

        @ToolParam(description = "商品编号", required = false)
        private String productId;
        @ToolParam(description = "商品名称", required = false)
        private String productName;
        @ToolParam(description = "商品品牌", required = false)
        private String brand;

        @ToolParam(description = "具体商品价格大小", required = false)
        private Integer price;

        @ToolParam(description = "商品状态，0-下架，1-上架，2-预售", required = false)
        private Integer status;

        @ToolParam(description = "排序方式", required = false)
        private ListSortEnum sortEnum;

        @ToolParam(description = "价格比较方式", required = false)
        private PriceCompareEnum priceCompareEnum;
    }

    @Transactional
    @Tool(description = "把排序(正序/倒序)转换为对应的枚举")
    public ListSortEnum getSortEnum(String sort) {

        log.info(String.format("| 参数: %s", sort));

        if (sort.equalsIgnoreCase(ListSortEnum.ASC.value)) {
            return ListSortEnum.ASC;
        } else {
            return ListSortEnum.DESC;
        }
    }

    @Transactional
    @Tool(description = "把商品价格比较（大于/小于/等于/大于等于/小于等于/高于/低于/不高于/不低于）转换为对应的枚举")
    public PriceCompareEnum getPriceCompareEnum(String priceCompare) {

        log.info(String.format("| 参数: %s", priceCompare));

        if (priceCompare.equalsIgnoreCase(PriceCompareEnum.GREATER_THAN.value)) {
            return PriceCompareEnum.GREATER_THAN;
        } else if (priceCompare.equalsIgnoreCase(PriceCompareEnum.LESS_THAN.value)) {
            return PriceCompareEnum.LESS_THAN;
        } else if (priceCompare.equalsIgnoreCase(PriceCompareEnum.GREATER_THAN_OR_EQUAL_TO.value)) {
            return PriceCompareEnum.GREATER_THAN_OR_EQUAL_TO;
        } else if (priceCompare.equalsIgnoreCase(PriceCompareEnum.LESS_THAN_OR_EQUAL_TO.value)) {
            return PriceCompareEnum.LESS_THAN_OR_EQUAL_TO;
        } else if (priceCompare.equalsIgnoreCase(PriceCompareEnum.HIGHER_THAN.value)) {
            return PriceCompareEnum.HIGHER_THAN;
        } else if (priceCompare.equalsIgnoreCase(PriceCompareEnum.LOWER_THAN.value)) {
            return PriceCompareEnum.LOWER_THAN;
        } else if (priceCompare.equalsIgnoreCase(PriceCompareEnum.NOT_HIGHER_THAN.value)) {
            return PriceCompareEnum.NOT_HIGHER_THAN;
        } else if (priceCompare.equalsIgnoreCase(PriceCompareEnum.NOT_LOWER_THAN.value)) {
            return PriceCompareEnum.NOT_LOWER_THAN;
        } else {
            return PriceCompareEnum.EQUAL_TO;
        }
    }

    @Tool(description = "根据条件查询商品信息")
    public List<Product> queryProductListByCondition(QueryProductRequest queryProductRequest) {
        log.info(String.format("| 参数: %s", queryProductRequest.toString()));

        return null;
    }
}
