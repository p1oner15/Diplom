package com.example.diplom.repository;

import com.example.diplom.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {


    List<Flight> findByDepartureAirportAndArrivalAirport(String departure, String arrival);
}
