package com.example.diplom.controller;

import com.example.diplom.domain.*;
import com.example.diplom.service.BookingService;
import com.example.diplom.service.ClientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        return ResponseEntity.of(Optional.ofNullable(client));
    }

    @PostMapping("/register")
    public ResponseEntity<Client> registerClient(@RequestBody RegisterDto registerDto) {
        Client registeredClient = clientService.registerClient(registerDto);
        return new ResponseEntity<>(registeredClient, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginClient(@RequestBody AuthDto authDto, HttpSession session) {
        Client authenticatedClient = clientService.authenticateClient(authDto.getEmail(), authDto.getPassword());
        if (authenticatedClient != null) {
            session.setAttribute("authenticatedClient", authenticatedClient);
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logoutClient(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }

    @PostMapping("/book-flight")
    public ResponseEntity<Booking> bookFlight(@RequestBody BookingRequestDto bookingRequestDto, HttpSession session) {
        // Получение данных о пользователе из текущей сессии
        BookingService bookingService = new BookingService();
        Client authenticatedClient = (Client) session.getAttribute("authenticatedClient");
        if (authenticatedClient == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Создание объекта бронирования
        Booking booking = new Booking();
        booking.setClientId(authenticatedClient.getClientId());
        booking.setFlightId(bookingRequestDto.getFlightId());
        booking.setBookingDate(LocalDateTime.now()); // Текущее время бронирования
        booking.setNumberOfPassengers(bookingRequestDto.getNumberOfPassengers());
        booking.setBookingStatus(BookingStatus.PENDING); // Начальный статус бронирования

        // Сохранение данных о бронировании в базу данных
        Booking savedBooking = bookingService.saveBooking(booking);

        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmailExists(@RequestParam String email) {
        boolean emailExists = clientService.checkEmailExists(email);
        return ResponseEntity.ok(emailExists);
    }

    @GetMapping("/check-password")
    public ResponseEntity<Boolean> checkPasswordMatches(@RequestParam String email, @RequestParam String password) {
        boolean passwordMatches = clientService.checkPasswordMatches(email, password);
        return ResponseEntity.ok(passwordMatches);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        Client client = clientService.updateClient(id, updatedClient);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
