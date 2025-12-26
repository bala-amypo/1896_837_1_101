package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Role {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
}
