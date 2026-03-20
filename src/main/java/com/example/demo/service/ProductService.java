package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.dto.request.CreateProductRequest;
import com.example.demo.dto.response.CreateProductResponse;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
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

        product.setStatus(createProductRequest.getStatus());
        product.setStock(createProductRequest.getStock());

        Product savedProduct = productRepository.save(product);

        return new CreateProductResponse(savedProduct.getProductId(), savedProduct.getProductName(),
                savedProduct.getCategory().getCategoryId(),
                savedProduct.getCategory().getCategoryName(), savedProduct.getPrice(),
                savedProduct.getImageUrl(), savedProduct.getDescription(), savedProduct.getStatus(),
                savedProduct.getStock(), savedProduct.getCreatedAt());
    }
}
