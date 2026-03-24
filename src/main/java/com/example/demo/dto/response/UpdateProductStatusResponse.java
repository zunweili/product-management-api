package com.example.demo.dto.response;

import com.example.demo.enums.ProductStatus;

public record UpdateProductStatusResponse(Long productId, String productName,
        ProductStatus status) {

}
