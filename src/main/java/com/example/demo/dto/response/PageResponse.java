package com.example.demo.dto.response;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

public record PageResponse<T>(@Schema(description = "目前頁面的資料列表") List<T> content,
        @Schema(description = "目前頁碼，從 0 開始", example = "0") int page,
        @Schema(description = "每頁筆數", example = "5") int size,
        @Schema(description = "總資料筆數", example = "23") long totalElements,
        @Schema(description = "總頁數", example = "5") int totalPages,
        @Schema(description = "是否為第一頁", example = "true") boolean first,
        @Schema(description = "是否為最後一頁", example = "false") boolean last) {

}
