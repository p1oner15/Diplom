package com.example.diplom.controller;

import com.example.diplom.domain.Booking;
import com.example.diplom.domain.BookingRequest;
import com.example.diplom.domain.BookingStatus;
import com.example.diplom.domain.Client;
import com.example.diplom.service.BookingService;
import com.example.diplom.service.ClientService;
import com.example.diplom.service.UserDetailsImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/bookings")
@SessionAttributes("client")
public class BookingController {

    @Autowired
    private final BookingService bookingService;

    private final ClientService clientService;


    public BookingController(BookingService bookingService, ClientService clientService) {
        this.bookingService = bookingService;
        this.clientService = clientService;
    }

    @GetMapping("/search")
    public String showSearchPage(HttpSession session, RedirectAttributes redirectAttributes) {
        // Проверяем, аутентифицирован ли пользователь
        if (session.getAttribute("client") == null) {
            redirectAttributes.addFlashAttribute("message", "Please login first");
            return "redirect:/login"; // Перенаправляем пользователя на страницу входа
        }
        return "search";
    }

    @PostMapping("/book")
    public ResponseEntity<?> bookFlight(@RequestBody BookingRequest bookingRequest, @AuthenticationPrincipal UserDetailsImpl client) {
        if (client == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login first");
        }

        Booking booking = new Booking();
        booking.setClientId(client.getId());
        booking.setFlightId(bookingRequest.getFlightId());
        booking.setBookingDate(LocalDateTime.now());
        booking.setNumberOfPassengers(bookingRequest.getNumberOfPassengers());
        booking.setBookingStatus(BookingStatus.valueOf("CONFIRMED"));
        booking.setPaymentAmount(BigDecimal.valueOf(1000.00)); // Пример суммы

        bookingService.createBooking(booking);
        return ResponseEntity.ok("Booking successful");
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Завершение сессии при выходе из системы
        return "redirect:/login";
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
        return bookingService.updateBooking(id, updatedBooking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}