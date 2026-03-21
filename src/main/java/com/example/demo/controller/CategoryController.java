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
import com.example.demo.dto.response.PageResponse;
import com.example.demo.dto.response.UpdateCategoryResponse;
import com.example.demo.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity<CreateCategoryResponse> createCategory(
            @RequestBody @Valid CreateCategoryRequest categoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.createCategory(categoryRequest));
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @PathVariable @Min(value = 1, message = "categoryId 必須大於或等於 1") Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.getCategoryById(categoryId));
    }

    @GetMapping("/categories")
    public ResponseEntity<PageResponse<CategoryResponse>> searchCategoriesByName(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "起始頁碼必須大於或等於 0") int page,
            @RequestParam(defaultValue = "5") @Min(value = 1, message = "每頁筆數必須大於或等於 1") int size) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.searchCategories(keyword, page, size));
    }

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<UpdateCategoryResponse> updateCategory(
            @PathVariable @Min(value = 1, message = "categoryId 必須大於或等於 1") Long categoryId,
            @RequestBody @Valid UpdateCategoryRequest updateCategoryRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.updateCategory(categoryId, updateCategoryRequest));
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable @Min(value = 1, message = "categoryId 必須大於或等於 1") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
