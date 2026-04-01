package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Product;
import com.example.demo.enums.ProductStatus;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductIdAndStatus(Long productId, ProductStatus status);

    Page<Product> findByStatus(ProductStatus status, Pageable pageable);

    Page<Product> findByStatusAndProductNameContainingIgnoreCase(ProductStatus status,
            String keyword, Pageable pageable);

    boolean existsByCategory_CategoryId(Long categoryId);
}
