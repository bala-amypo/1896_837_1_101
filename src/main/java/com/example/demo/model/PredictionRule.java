package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "prediction_rules", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ruleName")
})
@Getter
@Setter
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

    @Column(nullable = false)
    private Integer minDailyUsage;

    @Column(nullable = false)
    private Integer maxDailyUsage;

    private LocalDateTime createdAt;
}
