package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequest {

    @NotBlank(message = "商品類別名稱不能空白")
    @Size(max = 50, message = "商品類別名稱不能超過50字")
    private String categoryName;
}
