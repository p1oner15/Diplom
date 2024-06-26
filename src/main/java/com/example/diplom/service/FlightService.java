package com.example.diplom.service;

import com.example.diplom.domain.Flight;
import com.example.diplom.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(new Flight());
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long id, Flight updatedFlight) {
        if (flightRepository.existsById(id)) {
            updatedFlight.setFlightId(id);
            return flightRepository.save(updatedFlight);
        }
        return new Flight();
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public List<Flight> search(String departure, String arrival) {
        return flightRepository.findByDepartureAirportAndArrivalAirport(departure, arrival);
    }

    public BigDecimal getFlightPrice(Long flightId) {
        return flightRepository.findById(flightId)
                .map(Flight::getTicketPrice) // Предполагается, что в классе Flight есть метод getTicketPrice
                .orElse(BigDecimal.ZERO);
    }
}