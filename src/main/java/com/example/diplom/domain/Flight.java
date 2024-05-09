package com.example.diplom.domain;

import lombok.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Flights")
@Data
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureDatetime;
    private LocalDateTime arrivalDatetime;
    private int availableSeats;
    private String aircraftType;
    private BigDecimal ticketPrice;
}
