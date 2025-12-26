package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "prediction_rules", uniqueConstraints = @UniqueConstraint(columnNames = "ruleName"))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredictionRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleName;

    @Column(nullable = false)
    private Integer averageDaysWindow;

    @Column(nullable = false)
    private Integer minDailyUsage;

    @Column(nullable = false)
    private Integer maxDailyUsage;

    private LocalDateTime createdAt;
}