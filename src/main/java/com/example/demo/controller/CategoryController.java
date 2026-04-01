package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.request.CreateCategoryRequest;
import com.example.demo.dto.request.UpdateCategoryRequest;
import com.example.demo.dto.response.CategoryResponse;
import com.example.demo.dto.response.CreateCategoryResponse;
import com.example.demo.dto.response.ErrorResponse;
import com.example.demo.dto.response.PageResponse;
import com.example.demo.dto.response.UpdateCategoryResponse;
import com.example.demo.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
@Validated
@Tag(name = "商品類別管理")
public class CategoryController {

    private final CategoryService categoryService;

    // @formatter:off
    @Operation(
        summary = "建立商品類別",
        description = "新增一筆商品類別資料，商品類別名稱不可空白、不可超過 50 字且不可重複",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "建立成功",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CreateCategoryResponse.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "請求資料驗證失敗",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(
                        value = """
                                {
                                    "httpStatusCode": 400,
                                    "message": "商品類別名稱不能空白"
                                }
                                """
                    )
                )
            ),
            @ApiResponse(
                responseCode = "409",
                description = "商品類別名稱重複",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(
                        value = """
                                {
                                    "httpStatusCode": 409,
                                    "message": "此商品類別名稱已經被使用"
                                }
                                """
                    )
                )
            )
        }
    )
    // @formatter:on
    @PostMapping("/admin/categories")
    public ResponseEntity<CreateCategoryResponse> createCategory(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "建立商品類別的請求資料",
                    required = true) @RequestBody @Valid CreateCategoryRequest categoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.createCategory(categoryRequest));
    }

    // @formatter:off
    @Operation(
        summary = "依 ID 查詢商品類別",
        description = "依據商品類別 ID 查詢單筆商品類別資料，如果查不到對應資料就回傳 404",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "查詢成功",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CategoryResponse.class)
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "查詢的商品類別 ID 不存在",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(
                        value = """
                                {
                                    "httpStatusCode": 404,
                                    "message": "查無此商品類別"
                                }
                                """
                    )
                )
            )
        }
    )
    // @formatter:on
    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @Parameter(description = "商品類別 ID", example = "2") @PathVariable Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.getCategoryById(categoryId));
    }

    // @formatter:off
    @Operation(
        summary = "查詢商品類別列表",
        description = "依關鍵字查詢商品類別，並以分頁方式回傳結果；若未提供關鍵字，則查詢全部商品類別",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "查詢成功",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PageResponse.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "請求資料驗證失敗",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(
                        value = """
                                {
                                    "httpStatusCode": 400,
                                    "message": "起始頁碼必須大於或等於 0"
                                }
                                """
                    )
                )
            )
        }
    )
    // @formatter:on
    @GetMapping("/categories")
    public ResponseEntity<PageResponse<CategoryResponse>> searchCategoriesByName(
            @Parameter(description = "關鍵字",
                    example = "小說") @RequestParam(required = false) String keyword,
            @Parameter(description = "頁碼，從 0 開始", example = "0") @RequestParam(
                    defaultValue = "0") @Min(value = 0, message = "起始頁碼必須大於或等於 0") int page,
            @Parameter(description = "每頁筆數，最少為 1", example = "5") @RequestParam(
                    defaultValue = "5") @Min(value = 1, message = "每頁筆數必須大於或等於 1") int size) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.searchCategories(keyword, page, size));
    }

    // @formatter:off
    @Operation(
        summary = "更新商品類別",
        description = "依據商品類別 ID 更新商品類別名稱，名稱不可空白、不可超過 50 字且不可重複",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "更新成功",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UpdateCategoryResponse.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "請求資料驗證失敗",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(
                        value = """
                                {
                                    "httpStatusCode": 400,
                                    "message": "商品類別名稱不能空白"
                                }
                                """
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "找不到對應的商品類別",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(
                        value = """
                                {
                                    "httpStatusCode": 404,
                                    "message": "找不到此商品類別"
                                }
                                """
                    )
                )
            ),
            @ApiResponse(
                responseCode = "409",
                description = "商品類別名稱重複",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(
                        value = """
                                {
                                    "httpStatusCode": 409,
                                    "message": "此商品類別名稱已經被使用"
                                }
                                """
                    )
                )
            )
        }
    )
    // @formatter:on
    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<UpdateCategoryResponse> updateCategory(
            @Parameter(description = "商品類別 ID", example = "1") @PathVariable Long categoryId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "更新商品類別的請求資料",
                    required = true) @RequestBody @Valid UpdateCategoryRequest updateCategoryRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.updateCategory(categoryId, updateCategoryRequest));
    }

    // @formatter:off
    @Operation(
        summary = "刪除商品類別",
        description = "依據商品類別 ID 刪除指定商品類別，刪除成功後不回傳任何內容",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "刪除成功"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "找不到對應的商品類別",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(
                        value = """
                                {
                                    "httpStatusCode": 404,
                                    "message": "找不到此商品類別"
                                }
                                """
                    )
                )
            )
        }
    )
    // @formatter:on
    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<Void> deleteCategory(
            @Parameter(description = "商品類別 ID", example = "1") @PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
