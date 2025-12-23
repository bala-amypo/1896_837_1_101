package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "prediction_rules", uniqueConstraints = @UniqueConstraint(columnNames = "ruleName"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredictionRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleName;

    @Column(nullable = false)
    private Integer averageDaysWindow;

    private Integer minDailyUsage;

    private Integer maxDailyUsage;

    private LocalDateTime createdAt;
}