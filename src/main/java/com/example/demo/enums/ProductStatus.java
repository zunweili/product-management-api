package com.example.demo.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "商品狀態")
public enum ProductStatus {

    @Schema(description = "上架")
    ACTIVE,

    @Schema(description = "下架")
    INACTIVE
}
