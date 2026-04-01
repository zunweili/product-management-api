package com.example.demo.dto.response;

import com.example.demo.enums.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateProductStatusResponse(
        @Schema(description = "商品 ID", example = "6") Long productId,

        @Schema(description = "商品名稱", example = "追風箏的孩子") String productName,

        @Schema(description = "商品狀態", example = "ACTIVE") ProductStatus status) {

}
