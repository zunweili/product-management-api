package com.example.demo.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductStockRequest {

    @NotNull(message = "商品庫存不能為空")
    @Min(value = 0, message = "庫存不能低於 0")
    private Integer stock;
}
