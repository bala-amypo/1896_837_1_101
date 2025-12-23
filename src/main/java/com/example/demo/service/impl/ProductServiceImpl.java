package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public Product createProduct(Product product) {
        if (product.getProductName() == null || product.getProductName().isBlank()) {
            throw new IllegalArgumentException("Product name must not be blank");
        }
        if (product.getSku() == null || product.getSku().isBlank()) {
            throw new IllegalArgumentException("SKU must not be blank");
        }
        repo.findBySku(product.getSku()).ifPresent(p -> {
            throw new IllegalArgumentException("SKU must be unique");
        });
        product.setCreatedAt(LocalDateTime.now());
        return repo.save(product);
    }

    @Override
    public Product getProduct(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }
}
