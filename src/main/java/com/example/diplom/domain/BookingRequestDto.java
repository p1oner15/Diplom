package com.example.diplom.domain;

public class BookingRequestDto {
    private int flightId;
    private int numberOfPassengers;

    // Геттеры и сеттеры
    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
}
