package com.example.diplom.domain;

import lombok.Data;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Bookings")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    private LocalDateTime bookingDate;
    private int numberOfPassengers;
    private BookingStatus bookingStatus;
    private BigDecimal paymentAmount;
    private String additionalServices;
}

