package com.example.demo.dto.response;

import java.time.OffsetDateTime;
import com.example.demo.enums.ProductStatus;

public record UpdateProductResponse(Long productId, String productName, Long categoryId,
        String categoryName, Integer price, String imageUrl, String description,
        ProductStatus status, Integer stock, OffsetDateTime updatedAt) {

}
