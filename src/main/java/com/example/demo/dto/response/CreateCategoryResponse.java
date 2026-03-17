package com.example.demo.dto.response;

import java.time.OffsetDateTime;

public record CreateCategoryResponse(Long categoryId, String categoryName,
                OffsetDateTime createdAt) {

}
