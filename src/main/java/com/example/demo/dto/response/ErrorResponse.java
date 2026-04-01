package com.example.demo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record ErrorResponse(@Schema(description = "HTTP 狀態碼", example = "404") int httpStatusCode,
        @Schema(description = "錯誤訊息", example = "找不到此商品類別") String message) {

}
