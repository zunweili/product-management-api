package com.example.demo.dto.response;

import java.time.OffsetDateTime;
import com.example.demo.enums.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public record ProductResponse(@Schema(description = "商品 ID", example = "2") Long productId,

        @Schema(description = "商品名稱", example = "嫌疑犯X的獻身") String productName,

        @Schema(description = "商品類別 ID", example = "1") Long categoryId,

        @Schema(description = "商品類別名稱", example = "文學小說") String categoryName,

        @Schema(description = "商品價格", example = "340") Integer price,

        @Schema(description = "商品圖片網址",
                example = "https://example.com/images/suspect_x.jpg") String imageUrl,

        @Schema(description = "商品描述", example = "充滿數學思維與情感張力的推理小說。") String description,

        @Schema(description = "商品狀態", example = "ACTIVE") ProductStatus status,

        @Schema(description = "商品庫存", example = "8") Integer stock,

        @Schema(description = "建立時間 (ISO 8601 格式)",
                example = "2026-03-29T10:30:00+08:00") OffsetDateTime createdAt,

        @Schema(description = "最後更新時間 (ISO 8601 格式)",
                example = "2026-03-29T15:45:00+08:00") OffsetDateTime updatedAt) {

}
