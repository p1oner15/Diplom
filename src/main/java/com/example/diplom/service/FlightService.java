package com.example.diplom.service;

import com.example.diplom.domain.Flight;
import com.example.diplom.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}