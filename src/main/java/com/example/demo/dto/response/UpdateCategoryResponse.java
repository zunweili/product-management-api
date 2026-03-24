package com.example.demo.dto.response;

import java.time.OffsetDateTime;

public record UpdateCategoryResponse(Long categoryId, String categoryName,
        OffsetDateTime updatedAt) {

}
