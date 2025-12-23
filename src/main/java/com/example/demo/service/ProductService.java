package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product get(Long id);
    List<Product> getAll();
    Product update(Long id, Product product);
    void delete(Long id);
}
