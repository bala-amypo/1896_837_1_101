package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_records", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"product_id", "warehouse_id"})
})
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class StockRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne @JoinColumn(name = "product_id")
    private Product product;
    
    @ManyToOne @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
    
    private Integer currentQuantity;
    private Integer reorderThreshold;
    private LocalDateTime lastUpdated;
}