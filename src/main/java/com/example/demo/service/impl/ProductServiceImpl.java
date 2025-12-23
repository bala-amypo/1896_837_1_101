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
    public Product create(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        return repo.save(product);
    }

    @Override
    public Product get(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public List<Product> getAll() {
        return repo.findAll();
    }

    @Override
    public Product update(Long id, Product product) {
        Product existing = get(id);
        existing.setProductName(product.getProductName());
        existing.setSku(product.getSku());
        existing.setCategory(product.getCategory());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        Product existing = get(id);
        repo.delete(existing);
    }
}
