package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "warehouses", uniqueConstraints = @UniqueConstraint(columnNames = "warehouseName"))
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Warehouse {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String warehouseName;

    @Column(nullable = false)
    private String location;

    private LocalDateTime createdAt;
}
