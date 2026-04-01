package com.example.demo.dto.response;

import java.time.OffsetDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateCategoryResponse(
        @Schema(description = "商品類別 ID", example = "1") Long categoryId,
        @Schema(description = "商品類別名稱", example = "英國文學") String categoryName,
        @Schema(description = "最後更新時間 (ISO 8601 格式)",
                example = "2026-03-09T16:53:55+08:00") OffsetDateTime updatedAt) {

}
