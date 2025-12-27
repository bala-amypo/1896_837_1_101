package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue
    private Long id;

    private String productName;
    private String sku;
    private LocalDateTime createdAt;
}

