package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter @Setter
@Builder
public class Role {
    @Id @GeneratedValue
    private Long id;
    private String name;
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
}

