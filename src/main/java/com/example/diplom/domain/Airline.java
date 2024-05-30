package com.example.diplom.domain;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "airlines")
@Data
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airlineId;

    private String airlineName;
    private String airlineCode;
    private String description;
    private int fleetSize;
    private String countryBased;
    private String contactInfo;
}
