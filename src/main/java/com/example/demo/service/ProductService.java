package com.example.demo.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demo.dto.request.CreateProductRequest;
import com.example.demo.dto.request.UpdateProductRequest;
import com.example.demo.dto.response.CreateProductResponse;
import com.example.demo.dto.response.PageResponse;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.dto.response.UpdateProductResponse;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.enums.ProductSortBy;
import com.example.demo.enums.ProductStatus;
import com.example.demo.enums.SortDirection;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {
        Category category = categoryRepository.findById(createProductRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("查無此商品類別"));

        Product product = new Product();
        product.setProductName(createProductRequest.getProductName().trim());
        product.setCategory(category);
        product.setPrice(createProductRequest.getPrice());

        String imageUrl = createProductRequest.getImageUrl();
        product.setImageUrl(imageUrl == null || imageUrl.trim().isEmpty() ? null : imageUrl.trim());

        String description = createProductRequest.getDescription();
        product.setDescription(
                description == null || description.trim().isEmpty() ? null : description.trim());

        ProductStatus productStatus = createProductRequest.getStatus();
        product.setStatus(productStatus == null ? ProductStatus.ACTIVE : productStatus);

        product.setStock(createProductRequest.getStock());

        Product savedProduct = productRepository.save(product);

        return new CreateProductResponse(savedProduct.getProductId(), savedProduct.getProductName(),
                savedProduct.getCategory().getCategoryId(),
                savedProduct.getCategory().getCategoryName(), savedProduct.getPrice(),
                savedProduct.getImageUrl(), savedProduct.getDescription(), savedProduct.getStatus(),
                savedProduct.getStock(), savedProduct.getCreatedAt());
    }

    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("查無此商品"));

        return new ProductResponse(product.getProductId(), product.getProductName(),
                product.getCategory().getCategoryId(), product.getCategory().getCategoryName(),
                product.getPrice(), product.getImageUrl(), product.getDescription(),
                product.getStatus(), product.getStock(), product.getCreatedAt(),
                product.getUpdatedAt());
    }

    public PageResponse<ProductResponse> searchProducts(String keyword, int page, int size,
            ProductSortBy productSortBy, SortDirection sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection.name()),
                productSortBy.getFieldName());

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> productPage;

        if (keyword == null || keyword.isBlank()) {
            productPage = productRepository.findAll(pageable);
        } else {
            productPage = productRepository.findByProductNameContainingIgnoreCase(keyword.trim(),
                    pageable);
        }

        List<ProductResponse> content = productPage.getContent().stream()
                .map((Product product) -> new ProductResponse(product.getProductId(),
                        product.getProductName(), product.getCategory().getCategoryId(),
                        product.getCategory().getCategoryName(), product.getPrice(),
                        product.getImageUrl(), product.getDescription(), product.getStatus(),
                        product.getStock(), product.getCreatedAt(), product.getUpdatedAt()))
                .toList();

        return new PageResponse<>(content, productPage.getNumber(), productPage.getSize(),
                productPage.getTotalElements(), productPage.getTotalPages(), productPage.isFirst(),
                productPage.isLast());
    }

    public UpdateProductResponse updateProduct(Long productId,
            UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("找不到此商品"));

        Category category = categoryRepository.findById(updateProductRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("查無此商品類別"));

        product.setProductName(updateProductRequest.getProductName().trim());
        product.setCategory(category);
        product.setPrice(updateProductRequest.getPrice());

        String imageUrl = updateProductRequest.getImageUrl();
        product.setImageUrl(imageUrl == null || imageUrl.isBlank() ? null : imageUrl.trim());

        String description = updateProductRequest.getDescription();
        product.setDescription(
                description == null || description.isBlank() ? null : description.trim());

        Product savedProduct = productRepository.save(product);

        return new UpdateProductResponse(savedProduct.getProductId(), savedProduct.getProductName(),
                savedProduct.getCategory().getCategoryId(),
                savedProduct.getCategory().getCategoryName(), savedProduct.getPrice(),
                savedProduct.getImageUrl(), savedProduct.getDescription(), savedProduct.getStatus(),
                savedProduct.getStock(), savedProduct.getUpdatedAt());
    }
}
