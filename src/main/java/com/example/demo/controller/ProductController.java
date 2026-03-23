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
import com.example.demo.dto.response.PageResponse;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.dto.response.UpdateProductResponse;
import com.example.demo.dto.response.UpdateProductStatusResponse;
import com.example.demo.dto.response.UpdateProductStockResponse;
import com.example.demo.enums.ProductSortBy;
import com.example.demo.enums.ProductStatus;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @PostMapping("/admin/products")
    public ResponseEntity<CreateProductResponse> createProduct(
            @RequestBody @Valid CreateProductRequest createProductRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(createProductRequest));
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResponse> getActiveProductById(
            @PathVariable @Min(value = 1, message = "productId 必須大於或等於 1") Long productId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getActiveProductById(productId));
    }


    @GetMapping("/admin/products/{productId}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable @Min(value = 1, message = "productId 必須大於或等於 1") Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(productId));
    }

    @GetMapping("/products")
    public ResponseEntity<PageResponse<ProductResponse>> searchActiveProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "起始頁碼必須大於或等於 0") int page,
            @RequestParam(defaultValue = "10") @Min(value = 1, message = "每頁筆數必須大於或等於 1") int size,
            @RequestParam(defaultValue = "CREATED_AT") ProductSortBy productSortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection) {

        return ResponseEntity.status(HttpStatus.OK).body(productService
                .searchActiveProducts(keyword, page, size, productSortBy, sortDirection));
    }

    @GetMapping("/admin/products")
    public ResponseEntity<PageResponse<ProductResponse>> searchProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "起始頁碼必須大於或等於 0") int page,
            @RequestParam(defaultValue = "10") @Min(value = 1, message = "每頁筆數必須大於或等於 1") int size,
            @RequestParam(defaultValue = "CREATED_AT") ProductSortBy productSortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection,
            @RequestParam(defaultValue = "ACTIVE") ProductStatus status) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.searchProducts(keyword,
                page, size, productSortBy, sortDirection, status));
    }

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<UpdateProductResponse> updateProduct(
            @PathVariable @Min(value = 1, message = "productId 必須大於或等於 1") Long productId,
            @RequestBody @Valid UpdateProductRequest updateProductRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.updateProduct(productId, updateProductRequest));
    }

    @PatchMapping("/admin/products/{productId}/stock")
    public ResponseEntity<UpdateProductStockResponse> updateProductStock(
            @PathVariable @Min(value = 1, message = "productId 必須大於或等於 1") Long productId,
            @RequestBody @Valid UpdateProductStockRequest updateProductStockRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.updateProductStock(productId, updateProductStockRequest));
    }

    @PatchMapping("/admin/products/{productId}/status")
    public ResponseEntity<UpdateProductStatusResponse> updateProductStatus(
            @PathVariable @Min(value = 1, message = "productId 必須大於或等於 1") Long productId,
            @RequestBody @Valid UpdateProductStatusRequest updateProductStatusRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.updateProductStatus(productId, updateProductStatusRequest));
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable @Min(value = 1, message = "productId 必須大於或等於 1") Long productId) {
        productService.deleteProduct(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
