package com.example.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequest {

    @Schema(description = "商品名稱", example = "Java 程式設計入門",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(max = 100, message = "商品名稱不能超過 100 字")
    @NotBlank(message = "商品名稱不能空白")
    private String productName;

    @Schema(description = "商品類別 ID", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商品類別 ID 不能為空")
    @Min(value = 1, message = "商品類別 ID 必須大於或等於 1")
    private Long categoryId;

    @Schema(description = "商品價格", example = "450", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "價格不能為空")
    @Min(value = 0, message = "價格不能低於 0")
    private Integer price;

    @Schema(description = "商品圖片網址", example = "https://example.com/images/java-book.jpg")
    @Size(max = 255, message = "圖片網址不能超過 255 字")
    private String imageUrl;

    @Schema(description = "商品描述", example = "適合初學者閱讀，內容涵蓋 Java 基礎語法與物件導向觀念")
    @Size(max = 1000, message = "商品描述不能超過 1000 字")
    private String description;
}
