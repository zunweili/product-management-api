package com.example.demo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByCategoryNameIgnoreCase(String categoryName);

    boolean existsByCategoryNameIgnoreCaseAndCategoryIdNot(String categoryName, Long categoryId);

    Page<Category> findByCategoryNameContainingIgnoreCase(String keyword, Pageable pageable);
}
