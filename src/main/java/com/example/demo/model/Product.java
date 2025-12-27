package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank; // Import this
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // FIX: Add @NotBlank so validation fails if name is empty
    @NotBlank(message = "Product name required") 
    private String productName;
    
    private String sku;
    
    private String category;
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() { createdAt = LocalDateTime.now(); }
}