package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Warehouse {
    @Id @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String warehouseName;
    private String location;
    private LocalDateTime createdAt;
}
