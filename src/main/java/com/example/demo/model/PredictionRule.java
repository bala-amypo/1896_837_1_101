package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "prediction_rules", uniqueConstraints = @UniqueConstraint(columnNames = "ruleName"))
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PredictionRule {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleName;

    private Integer averageDaysWindow;
    private Integer minDailyUsage;
    private Integer maxDailyUsage;
    private LocalDateTime createdAt;
}
