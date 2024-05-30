package com.example.diplom.service;

import com.example.diplom.domain.Booking;
import com.example.diplom.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(new Booking());
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking updatedBooking) {
        if (bookingRepository.existsById(id)) {
            updatedBooking.setBookingId(id);
            return bookingRepository.save(updatedBooking);
        }
        return new Booking();
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    // Метод для сохранения бронирования
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
}
