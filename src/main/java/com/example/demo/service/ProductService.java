package com.example.demo.service;

import com.example.demo.model.Product;
import java.util.List;

public interface ProductService {
    Product createProduct(Product p);
    Product getProduct(Long id);
    List<Product> getAllProducts();
}
