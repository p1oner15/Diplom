package com.example.diplom.domain;

import lombok.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Bookings")
@Data
@Getter
@Setter
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
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    private BigDecimal paymentAmount;
    private String additionalServices;

    public void setBookingDate(LocalDateTime date) {
        this.bookingDate = date;
    }

    public void setClientId(Long clientId) {
        if (client == null) {
            client = new Client(); // Создаем новый объект клиента, если его еще нет
        }
        client.setClientId(clientId);
    }

    // Метод для установки идентификатора рейса
    public void setFlightId(long flightId) {
        if (flight == null) {
            flight = new Flight(); // Создаем новый объект рейса, если его еще нет
        }
        flight.setFlightId(flightId);
    }
}

