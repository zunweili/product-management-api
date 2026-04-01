package com.example.demo.dto.request;

import com.example.demo.enums.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequest {

    @Schema(description = "商品名稱", example = "億元肥羊零成本買股術：勝率 100%！我靠借錢買金融股賺到 1 億",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(max = 100, message = "商品名稱不能超過 100 字")
    @NotBlank(message = "商品名稱不能空白")
    private String productName;

    @Schema(description = "商品類別 ID", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商品類別 ID 不能為空")
    @Min(value = 1, message = "商品類別 ID 必須大於或等於 1")
    private Long categoryId;

    @Schema(description = "商品價格", example = "450", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "價格不能為空")
    @Min(value = 0, message = "價格不能低於 0")
    private Integer price;

    @Schema(description = "商品圖片網址", example = "https://example.com/images/product-1.jpg")
    @Size(max = 255, message = "圖片網址不能超過 255 字")
    private String imageUrl;

    @Schema(description = "商品描述", example = "多數人存錢是為了生存，富人舉債是為了掠奪；人生最危險的賭注不是開槓桿，而是把命運交給薪水決定。")
    @Size(max = 1000, message = "商品描述不能超過 1000 字")
    private String description;

    @Schema(description = "商品狀態", example = "ACTIVE")
    private ProductStatus status;

    @Schema(description = "商品庫存數量", example = "13", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商品庫存不能為空")
    @Min(value = 0, message = "庫存不能低於 0")
    private Integer stock;
}
