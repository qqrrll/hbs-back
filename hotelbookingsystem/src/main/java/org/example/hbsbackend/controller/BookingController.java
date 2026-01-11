package org.example.hbsbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.dto.BookingCreateDTO;
import org.example.hbsbackend.dto.BookingDTO;
import org.example.hbsbackend.entity.Booking;
import org.example.hbsbackend.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;
    @GetMapping("/get-all-bookings")
    public List<BookingDTO> getAllBookings() {
        return bookingService.getAllBookings();
    }
    @GetMapping("/get-booking-by-id/{id}")
    public BookingDTO getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }
    @PostMapping("create-booking")
    public BookingDTO createBooking(@Valid @RequestBody BookingCreateDTO booking) {
        return bookingService.createBooking(booking);
    }
    @PutMapping("/update-booking/{id}")
    public BookingDTO updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        return bookingService.updateBooking(id,  bookingDTO);
    }
    @DeleteMapping("delete-booking/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}
