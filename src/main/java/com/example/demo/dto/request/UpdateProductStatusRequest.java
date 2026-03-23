package com.example.demo.dto.request;

import com.example.demo.enums.ProductStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductStatusRequest {
    @NotNull(message = "商品狀態不能為空")
    private ProductStatus status;
}
