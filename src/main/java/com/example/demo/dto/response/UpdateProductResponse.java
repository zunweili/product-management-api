package com.example.demo.dto.response;

import java.time.OffsetDateTime;
import com.example.demo.enums.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateProductResponse(@Schema(description = "商品 ID", example = "15") Long productId,

        @Schema(description = "商品名稱", example = "Java 程式設計入門") String productName,

        @Schema(description = "商品類別 ID", example = "3") Long categoryId,

        @Schema(description = "商品類別名稱", example = "程式設計") String categoryName,

        @Schema(description = "商品價格", example = "450") Integer price,

        @Schema(description = "商品圖片網址",
                example = "https://example.com/images/java-book.jpg") String imageUrl,

        @Schema(description = "商品描述", example = "適合初學者閱讀，內容涵蓋 Java 基礎語法與物件導向觀念") String description,

        @Schema(description = "商品狀態", example = "ACTIVE") ProductStatus status,

        @Schema(description = "商品庫存", example = "4") Integer stock,

        @Schema(description = "最後更新時間 (ISO 8601 格式)",
                example = "2026-03-30T10:30:00+08:00") OffsetDateTime updatedAt) {

}
