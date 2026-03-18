package com.team.hotelbooking.controller;

import com.team.hotelbooking.dtos.BookingRequestDTO;
import com.team.hotelbooking.dtos.BookingResponseDTO;
import com.team.hotelbooking.model.Booking;
import com.team.hotelbooking.service.BookingService;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        return ResponseEntity.ok("Booking with id " + id + " was successfully canceled.");
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

    @GetMapping("/available/{startDate}/{endDate}")
    public ResponseEntity<List<Long>> getAvailableRoomsByDate(
            @PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate
    ){
        List<Long> roomIds = bookingService.getAvailableRooms(startDate,endDate);
        return ResponseEntity.ok(roomIds);
    }
}