package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.dto.request.CreateCategoryRequest;
import com.example.demo.dto.response.CategoryResponse;
import com.example.demo.dto.response.CreateCategoryResponse;
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
}
