package com.example.diplom.controller;

import com.example.diplom.domain.Airline;
import com.example.diplom.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airlines")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @GetMapping
    public List<Airline> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    @GetMapping("/{id}")
    public Airline getAirlineById(@PathVariable Long id) {
        return airlineService.getAirlineById(id);
    }

    @PostMapping
    public Airline createAirline(@RequestBody Airline airline) {
        return airlineService.createAirline(airline);
    }

    @PutMapping("/{id}")
    public Airline updateAirline(@PathVariable Long id, @RequestBody Airline updatedAirline) {
        return airlineService.updateAirline(id, updatedAirline);
    }

    @DeleteMapping("/{id}")
    public void deleteAirline(@PathVariable Long id) {
        airlineService.deleteAirline(id);
    }
}


