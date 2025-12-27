package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
public class Warehouse {

    @Id @GeneratedValue
    private Long id;

    private String location;
    private LocalDateTime createdAt;
}
