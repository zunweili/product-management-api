package com.example.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequest {

    @Schema(description = "商品類別名稱", example = "青少年文學", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "商品類別名稱不能空白")
    @Size(max = 50, message = "商品類別名稱不能超過50字")
    private String categoryName;
}
