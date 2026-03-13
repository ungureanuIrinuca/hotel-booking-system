package com.team.hotelbooking.repository;

import com.team.hotelbooking.model.Booking;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class BookingRepository {

    private final List<Booking> bookings = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Booking save(Booking booking) {
        if (booking.getId() == null) {
            booking.setId(idGenerator.getAndIncrement());
            bookings.add(booking);
        }
        return booking;
    }

    public Optional<Booking> findById(Long id) {
        return bookings.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
    }

    public List<Booking> findByRoomId(Long roomId) {
        return bookings.stream()
                .filter(b -> b.getRoomId() != null && b.getRoomId().equals(roomId))
                .toList();
    }

    public List<Booking> findByGuestId(Long guestId) {
        return bookings.stream()
                .filter(b -> b.getGuestId() != null && b.getGuestId().equals(guestId))
                .toList();
    }

    public List<Booking> findByHostId(Long hostId) {
        return bookings.stream()
                .filter(b -> b.getHostId() != null && b.getHostId().equals(hostId))
                .toList();
    }

    public List<Booking> findByStartDateAfter(LocalDate date) {
        return bookings.stream()
                .filter(b -> b.getStartDate() != null && b.getStartDate().isAfter(date))
                .toList();
    }
}