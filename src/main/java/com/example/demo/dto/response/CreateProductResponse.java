package com.example.demo.dto.response;

import java.time.OffsetDateTime;
import com.example.demo.enums.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public record CreateProductResponse(@Schema(description = "商品 ID", example = "17") Long productId,

        @Schema(description = "商品名稱",
                example = "億元肥羊零成本買股術：勝率 100%！我靠借錢買金融股賺到 1 億") String productName,

        @Schema(description = "商品類別 ID", example = "2") Long categoryId,

        @Schema(description = "商品類別名稱", example = "商業理財") String categoryName,

        @Schema(description = "商品價格", example = "450") Integer price,

        @Schema(description = "商品圖片網址",
                example = "https://example.com/images/product-1.jpg") String imageUrl,

        @Schema(description = "商品描述",
                example = "多數人存錢是為了生存，富人舉債是為了掠奪；人生最危險的賭注不是開槓桿，而是把命運交給薪水決定。") String description,

        @Schema(description = "商品狀態", example = "ACTIVE") ProductStatus status,

        @Schema(description = "商品庫存數量", example = "13") Integer stock,

        @Schema(description = "商品建立時間（ISO 8601 格式）",
                example = "2026-03-29T10:30:00+08:00") OffsetDateTime createdAt) {

}
