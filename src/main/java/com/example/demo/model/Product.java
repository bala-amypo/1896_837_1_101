package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = "sku"))
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Product {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false, unique = true)
    private String sku;

    private String category;

    private LocalDateTime createdAt;
}
