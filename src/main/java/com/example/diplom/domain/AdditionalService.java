package com.example.diplom.domain;

import lombok.Data;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "AdditionalServices")
@Data
public class AdditionalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    private String serviceName;
    private String description;
    private BigDecimal price;
}
