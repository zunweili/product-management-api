package com.example.demo.dto.response;

import java.time.OffsetDateTime;

public record CategoryResponse(Long categoryId, String categoryName, OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {

}
