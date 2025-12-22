package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(unique = true, nullable = false)
    private String sku;

    private String category;
    private LocalDateTime createdAt;
}

@Entity
@Table(name = "warehouses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String warehouseName;

    @Column(nullable = false)
    private String location;

    private LocalDateTime createdAt;
}

@Entity
@Table(name = "stock_records", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"product_id", "warehouse_id"})
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Product product;

    @ManyToOne(optional = false)
    private Warehouse warehouse;

    private Integer currentQuantity;
    private Integer reorderThreshold;
    private LocalDateTime lastUpdated;
}

@Entity
@Table(name = "consumption_logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private StockRecord stockRecord;

    private Integer consumedQuantity;
    private LocalDate consumedDate;
}

@Entity
@Table(name = "prediction_rules")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredictionRule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String ruleName;

    private Integer averageDaysWindow;
    private Integer minDailyUsage;
    private Integer maxDailyUsage;
    private LocalDateTime createdAt;
}

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    private LocalDateTime createdAt;
}

public enum Role {
    ROLE_USER, ROLE_ADMIN
}
