package com.example.demo.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "商品排序欄位")
public enum ProductSortBy {

    @Schema(description = "依商品價格排序")
    PRICE("price"),

    @Schema(description = "依商品建立時間排序")
    CREATED_AT("createdAt");

    private final String fieldName;

    ProductSortBy(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
