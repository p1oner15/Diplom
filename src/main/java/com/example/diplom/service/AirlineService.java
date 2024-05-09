package com.example.diplom.service;

import com.example.diplom.domain.Airline;
import com.example.diplom.repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    public Airline getAirlineById(Long id) {
        return airlineRepository.findById(id).orElse(null);
    }

    public Airline createAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    public Airline updateAirline(Long id, Airline updatedAirline) {
        if (airlineRepository.existsById(id)) {
            updatedAirline.setAirlineId(id);
            return airlineRepository.save(updatedAirline);
        }
        return null;
    }

    public void deleteAirline(Long id) {
        airlineRepository.deleteById(id);
    }
}
