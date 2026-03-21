package com.example.demo.dto.request;

import com.example.demo.enums.ProductStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequest {

    @Size(max = 100, message = "商品名稱不能超過 100 字")
    @NotBlank(message = "商品名稱不能空白")
    private String productName;

    @NotNull(message = "商品類別 ID 不能為空")
    @Min(value = 1, message = "商品類別 ID 必須大於或等於 1")
    private Long categoryId;

    @NotNull(message = "價格不能為空")
    @Min(value = 0, message = "價格不能低於 0")
    private Integer price;

    @Size(max = 255, message = "圖片檔名不能超過 255 字")
    private String imageUrl;

    @Size(max = 1000, message = "商品描述不能超過 1000 字")
    private String description;

    private ProductStatus status;

    @NotNull(message = "商品庫存不能為空")
    @Min(value = 0, message = "庫存不能低於 0")
    private Integer stock;
}
