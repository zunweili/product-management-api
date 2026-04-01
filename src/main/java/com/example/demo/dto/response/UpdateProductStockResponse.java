package com.example.demo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateProductStockResponse(
        @Schema(description = "商品 ID", example = "3") Long productId,

        @Schema(description = "商品名稱", example = "挪威的森林") String productName,

        @Schema(description = "商品庫存", example = "50") Integer stock) {

}
