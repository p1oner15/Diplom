package com.example.diplom.controller;

import com.example.diplom.domain.Flight;
import com.example.diplom.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.createFlight(flight);
    }

    @PutMapping("/{id}")
    public Flight updateFlight(@PathVariable Long id, @RequestBody Flight updatedFlight) {
        return flightService.updateFlight(id, updatedFlight);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
}
