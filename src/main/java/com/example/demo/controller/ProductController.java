package com.example.demo.controller;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.request.CreateProductRequest;
import com.example.demo.dto.request.UpdateProductRequest;
import com.example.demo.dto.request.UpdateProductStatusRequest;
import com.example.demo.dto.request.UpdateProductStockRequest;
import com.example.demo.dto.response.CreateProductResponse;
import com.example.demo.dto.response.ErrorResponse;
import com.example.demo.dto.response.PageResponse;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.dto.response.UpdateProductResponse;
import com.example.demo.dto.response.UpdateProductStatusResponse;
import com.example.demo.dto.response.UpdateProductStockResponse;
import com.example.demo.enums.ProductSortBy;
import com.example.demo.enums.ProductStatus;
import com.example.demo.service.ProductService;
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

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
@Tag(name = "商品管理")
public class ProductController {

    private final ProductService productService;

    // @formatter:off
    @Operation(
        summary = "建立商品",
        description = "新增一筆商品資料，商品名稱不可空白且不可超過 100 字，商品類別 ID 必須存在，價格不得低於 0，庫存不得低於 0；圖片網址與商品描述會在寫入前去除前後空白，若空白則存為 null，商品狀態若未提供則預設為 ACTIVE",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "建立成功",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CreateProductResponse.class)
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
                                    "message": "商品名稱不能空白"
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
                                    "message": "查無此商品類別"
                                }
                                """
                    )
                )
            )
        }
    )
    // @formatter:on
    @PostMapping("/admin/products")
    public ResponseEntity<CreateProductResponse> createProduct(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "建立商品的請求資料",
                    required = true) @RequestBody @Valid CreateProductRequest createProductRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(createProductRequest));
    }

    // @formatter:off
    @Operation(
        summary = "依 ID 查詢上架商品",
        description = "依據商品 ID 查詢單筆狀態為 ACTIVE 的商品資料，若商品不存在或商品不是上架狀態，則回傳 404",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "查詢成功",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponse.class)
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
                                    "message": "productId 必須大於或等於 1"
                                }
                                """
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "找不到對應的上架商品",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(
                        value = """
                                {
                                    "httpStatusCode": 404,
                                    "message": "查無此商品"
                                }
                                """
                    )
                )
            )
        }
    )
    // @formatter:on
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResponse> getActiveProductById(
            @Parameter(description = "商品 ID", example = "2") @PathVariable @Min(value = 1,
                    message = "productId 必須大於或等於 1") Long productId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getActiveProductById(productId));
    }

    // @formatter:off
    @Operation(
        summary = "依 ID 查詢商品",
        description = "依據商品 ID 查詢單筆商品資料，若商品不存在，則回傳 404",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "查詢成功",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponse.class)
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
                                    "message": "productId 必須大於或等於 1"
                                }
                                """
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "找不到對應的商品",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(
                        value = """
                                {
                                    "httpStatusCode": 404,
                                    "message": "查無此商品"
                                }
                                """
                    )
                )
            )
        }
    )
    // @formatter:on
    @GetMapping("/admin/products/{productId}")
    public ResponseEntity<ProductResponse> getProductById(
            @Parameter(description = "商品 ID", example = "2") @PathVariable @Min(value = 1,
                    message = "productId 必須大於或等於 1") Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(productId));
    }

    // @formatter:off
    @Operation(
        summary = "查詢上架商品列表",
        description = "查詢狀態為 ACTIVE 的商品列表，支援依商品名稱關鍵字模糊搜尋，並可指定頁碼、每頁筆數、排序欄位與排序方向；若未提供關鍵字，則查詢全部上架商品",
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
    @GetMapping("/products")
    public ResponseEntity<PageResponse<ProductResponse>> searchActiveProducts(
            @Parameter(description = "商品名稱關鍵字，用於模糊搜尋；未提供則查詢全部上架商品",
                    example = "的") @RequestParam(required = false) String keyword,
            @Parameter(description = "頁碼，從 0 開始", example = "0") @RequestParam(
                    defaultValue = "0") @Min(value = 0, message = "起始頁碼必須大於或等於 0") int page,
            @Parameter(description = "每頁筆數，最少為 1", example = "3") @RequestParam(
                    defaultValue = "10") @Min(value = 1, message = "每頁筆數必須大於或等於 1") int size,
            @Parameter(description = "商品排序欄位，可選擇 PRICE 或 CREATED_AT",
                    example = "CREATED_AT") @RequestParam(
                            defaultValue = "CREATED_AT") ProductSortBy productSortBy,
            @Parameter(description = "排序方向，可選擇 ASC 或 DESC", example = "DESC") @RequestParam(
                    defaultValue = "DESC") Sort.Direction sortDirection) {

        return ResponseEntity.status(HttpStatus.OK).body(productService
                .searchActiveProducts(keyword, page, size, productSortBy, sortDirection));
    }

    // @formatter:off
    @Operation(
        summary = "查詢商品列表",
        description = "查詢商品資料列表，可依商品名稱關鍵字與商品狀態篩選，並支援分頁、排序欄位及排序方向；若未提供 keyword，則查詢指定狀態的全部商品",
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
                                    "message": "每頁筆數必須大於或等於 1"
                                }
                                """
                    )
                )
            )
        }
    )
    // @formatter:on
    @GetMapping("/admin/products")
    public ResponseEntity<PageResponse<ProductResponse>> searchProducts(
            @Parameter(description = "商品名稱關鍵字，用於模糊搜尋；未提供則查詢指定狀態的全部商品") @RequestParam(
                    required = false) String keyword,
            @Parameter(description = "頁碼，從 0 開始", example = "0") @RequestParam(
                    defaultValue = "0") @Min(value = 0, message = "起始頁碼必須大於或等於 0") int page,
            @Parameter(description = "每頁筆數，最少為 1", example = "10") @RequestParam(
                    defaultValue = "10") @Min(value = 1, message = "每頁筆數必須大於或等於 1") int size,
            @Parameter(description = "商品排序欄位，可選擇 PRICE 或 CREATED_AT",
                    example = "CREATED_AT") @RequestParam(
                            defaultValue = "CREATED_AT") ProductSortBy productSortBy,
            @Parameter(description = "排序方向，可選擇 ASC 或 DESC", example = "DESC") @RequestParam(
                    defaultValue = "DESC") Sort.Direction sortDirection,
            @Parameter(description = "商品狀態，可選擇 ACTIVE 或 INACTIVE",
                    example = "INACTIVE") @RequestParam(
                            defaultValue = "ACTIVE") ProductStatus status) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.searchProducts(keyword,
                page, size, productSortBy, sortDirection, status));
    }

    // @formatter:off
    @Operation(
        summary = "更新商品",
        description = "依據商品 ID 更新指定商品資料。商品名稱不可空白且不可超過 100 字，商品類別 ID 必須大於或等於 1，價格不得低於 0，圖片網址不可超過 255 字，商品描述不可超過 1000 字；若請求資料驗證失敗則回傳 400，若商品不存在或指定的商品類別不存在則回傳 404",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "更新成功",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UpdateProductResponse.class)
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
                                    "message": "價格不能低於 0"
                                }
                                """
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "找不到對應的商品",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(
                        value = """
                                {
                                    "httpStatusCode": 404,
                                    "message": "查無此商品"
                                }
                                """
                    )
                )
            )
        }
    )
    // @formatter:on
    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<UpdateProductResponse> updateProduct(
            @Parameter(description = "商品 ID", example = "15") @PathVariable @Min(value = 1,
                    message = "productId 必須大於或等於 1") Long productId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "更新商品的請求資料",
                    required = true) @RequestBody @Valid UpdateProductRequest updateProductRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.updateProduct(productId, updateProductRequest));
    }

    // @formatter:off
    @Operation(
        summary = "更新商品庫存",
        description = "依據商品 ID 更新指定商品的庫存數量，商品 ID 必須大於或等於 1，庫存不得低於 0；若請求資料驗證失敗則回傳 400，若商品不存在則回傳 404",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "更新成功",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UpdateProductStockResponse.class)
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
                                    "message": "商品庫存不能為空"
                                }
                                """
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "找不到對應的商品",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(
                        value = """
                                {
                                    "httpStatusCode": 404,
                                    "message": "查無此商品"
                                }
                                """
                    )
                )
            )
        }
    )
    // @formatter:on
    @PatchMapping("/admin/products/{productId}/stock")
    public ResponseEntity<UpdateProductStockResponse> updateProductStock(
            @Parameter(description = "商品 ID", example = "3") @PathVariable @Min(value = 1,
                    message = "productId 必須大於或等於 1") Long productId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "更新商品庫存的請求資料",
                    required = true) @RequestBody @Valid UpdateProductStockRequest updateProductStockRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.updateProductStock(productId, updateProductStockRequest));
    }

    // @formatter:off
    @Operation(
        summary = "更新商品狀態",
        description = "依據商品 ID 更新指定商品的狀態，商品 ID 必須大於或等於 1，商品狀態不能為空；若請求資料驗證失敗則回傳 400，若商品不存在則回傳 404",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "更新成功",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UpdateProductStatusResponse.class)
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
                                    "message": "商品狀態不能為空"
                                }
                                """
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "找不到對應的商品",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(
                        value = """
                                {
                                    "httpStatusCode": 404,
                                    "message": "查無此商品"
                                }
                                """
                    )
                )
            )
        }
    )
    // @formatter:on
    @PatchMapping("/admin/products/{productId}/status")
    public ResponseEntity<UpdateProductStatusResponse> updateProductStatus(
            @Parameter(description = "商品 ID", example = "6") @PathVariable @Min(value = 1,
                    message = "productId 必須大於或等於 1") Long productId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "更新商品狀態的請求資料",
                    required = true) @RequestBody @Valid UpdateProductStatusRequest updateProductStatusRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.updateProductStatus(productId, updateProductStatusRequest));
    }

    // @formatter:off
    @Operation(
        summary = "刪除商品",
        description = "依據商品 ID 刪除指定商品資料，商品 ID 必須大於或等於 1；若商品不存在則回傳 404，刪除成功後不回傳回應內容",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "刪除成功"
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
                                    "message": "productId 必須大於或等於 1"
                                }
                                """
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "找不到對應的商品",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(
                        value = """
                                {
                                    "httpStatusCode": 404,
                                    "message": "查無此商品"
                                }
                                """
                    )
                )
            )
        }
    )
    // @formatter:on
    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "商品 ID", example = "18") @PathVariable @Min(value = 1,
                    message = "productId 必須大於或等於 1") Long productId) {
        productService.deleteProduct(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
