package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "consumption_logs")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ConsumptionLog {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private StockRecord stockRecord;

    private Integer consumedQuantity;
    private LocalDate consumedDate;
}
