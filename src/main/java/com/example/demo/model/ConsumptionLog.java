package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
public class ConsumptionLog {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private StockRecord stockRecord;

    private Integer quantityConsumed;
    private LocalDate consumedDate;
}
