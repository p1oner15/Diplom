package com.example.diplom.controller;

import com.example.diplom.domain.Flight;
import com.example.diplom.repository.FlightRepository;
import com.example.diplom.service.FlightService;
import com.example.diplom.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/cities")
    public List<Flight> getFlights(@RequestParam String departure, @RequestParam String arrival) {
        return flightRepository.findByDepartureAirportAndArrivalAirport(departure, arrival);
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }
    @GetMapping("/search")
    public List<Flight> getFlightById(String departure, String arrival, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    userDetails.getId();
        return flightService.search(departure, arrival);
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
    @GetMapping("/departureCities")
    public List<String> getDepartureCities() {
        List<String> departureCities = flightRepository.findAll()
                .stream()
                .map(Flight::getDepartureAirport)
                .distinct()
                .collect(Collectors.toList());
        return departureCities;
    }

    // Метод для получения списка уникальных городов прибытия
    @GetMapping("/arrivalCities")
    public List<String> getArrivalCities() {
        List<String> arrivalCities = flightRepository.findAll()
                .stream()
                .map(Flight::getArrivalAirport)
                .distinct()
                .collect(Collectors.toList());
        return arrivalCities;
    }
}
