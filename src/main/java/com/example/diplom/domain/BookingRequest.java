package com.example.diplom.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingRequest {
    private Long flightId;
    private int numberOfPassengers;
    private List<String> additionalServices;
}