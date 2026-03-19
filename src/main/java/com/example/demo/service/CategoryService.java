package com.example.demo.service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demo.dto.request.CreateCategoryRequest;
import com.example.demo.dto.response.CategoryResponse;
import com.example.demo.dto.response.CreateCategoryResponse;
import com.example.demo.dto.response.PageResponse;
import com.example.demo.entity.Category;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CreateCategoryResponse createCategory(CreateCategoryRequest createCategoryRequest) {
        String categoryName = createCategoryRequest.getCategoryName().trim();

        if (categoryRepository.existsByCategoryNameIgnoreCase(categoryName)) {
            throw new DuplicateResourceException("類別已經存在");
        }

        Category category = new Category(categoryName);

        Category savedCategory = categoryRepository.save(category);

        return new CreateCategoryResponse(savedCategory.getCategoryId(),
                savedCategory.getCategoryName(), savedCategory.getCreatedAt());
    }

    public CategoryResponse getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("查無此商品類別"));

        return new CategoryResponse(category.getCategoryId(), category.getCategoryName(),
                category.getCreatedAt(), category.getUpdatedAt());
    }

    public PageResponse<CategoryResponse> searchCategoriesByName(String keyword, int page,
            int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<Category> categoryPage;

        if (keyword == null || keyword.isBlank()) {
            categoryPage = categoryRepository.findAll(pageable);
        } else {
            categoryPage = categoryRepository.findByCategoryNameContainingIgnoreCase(keyword.trim(),
                    pageable);
        }

        List<CategoryResponse> content = categoryPage.getContent().stream()
                .map((Category category) -> new CategoryResponse(category.getCategoryId(),
                        category.getCategoryName(), category.getCreatedAt(),
                        category.getUpdatedAt()))
                .toList();

        return new PageResponse<>(content, categoryPage.getNumber(), categoryPage.getSize(),
                categoryPage.getTotalElements(), categoryPage.getTotalPages(),
                categoryPage.isFirst(), categoryPage.isLast());
    }
}
