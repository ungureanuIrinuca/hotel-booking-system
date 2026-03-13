package com.team.hotelbooking.controller;

import com.team.hotelbooking.dtos.BookingRequestDTO;
import com.team.hotelbooking.dtos.BookingResponseDTO;
import com.team.hotelbooking.model.Booking;
import com.team.hotelbooking.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingRequestDTO request) {
        BookingResponseDTO response = bookingService.createBooking(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Rezervarea cu ID-ul " + id + " a fost anulată cu succes.");
    }

    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<Booking>> getGuestBookings(@PathVariable Long guestId) {
        List<Booking> userBookings = bookingService.getByGuest(guestId);
        return ResponseEntity.ok(userBookings);
    }

    @GetMapping("/host/{hostId}")
    public ResponseEntity<List<Booking>> getHostBookings(@PathVariable Long hostId) {
        List<Booking> hostBookings = bookingService.getByHost(hostId);
        return ResponseEntity.ok(hostBookings);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<BookingResponseDTO>> getUpcomingBookings() {
        List<BookingResponseDTO> upcoming = bookingService.getUpcomingBookings();
        return ResponseEntity.ok(upcoming);
    }
}