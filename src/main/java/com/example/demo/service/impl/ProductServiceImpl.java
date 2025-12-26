package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
    public Product createProduct(Product product) { return product; }
    public Product getProduct(Long id) { return new Product(); }
    public List<Product> getAllProducts() { return List.of(); }
}
