package com.team.hotelbooking.service;

import com.team.hotelbooking.dtos.BookingRequestDTO;
import com.team.hotelbooking.dtos.BookingResponseDTO;
import com.team.hotelbooking.model.Booking;
import com.team.hotelbooking.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public BookingResponseDTO createBooking(BookingRequestDTO request) {
        Long roomId = request.getRoomId();
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();

        if (!startDate.isBefore(endDate))
            throw new RuntimeException("Invalid date interval.");

        if (startDate.isBefore(LocalDate.now()))
            throw new RuntimeException("Booking cannot start in the past.");

        if (!checkAvailability(roomId, startDate, endDate))
            throw new RuntimeException("Room not available.");

        Booking booking = new Booking();
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setRoomId(roomId);

        booking.setGuestId(request.getGuestId());
        booking.setHostId(request.getHostId());

        booking.setStatus(Booking.BookingStatus.ACTIVE);

        Booking saved = bookingRepository.save(booking);
        return mapToResponse(saved);
    }

    private boolean checkAvailability(Long roomId, LocalDate startDate, LocalDate endDate) {

        List<Booking> roomBookings = bookingRepository.findByRoomId(roomId);

        for (Booking b : roomBookings) {
            boolean overlap =
                    startDate.isBefore(b.getEndDate()) &&
                            endDate.isAfter(b.getStartDate());

            if (overlap && b.getStatus() == Booking.BookingStatus.ACTIVE)
                return false;
        }
        return true;


    }

    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking with id " + id + " does not exist."));

        booking.setStatus(Booking.BookingStatus.CANCELLED);
        bookingRepository.save(booking);


    }

    public List<Booking> getByGuest(Long guestId) {
        return bookingRepository.findByGuestId(guestId);
    }

    public List<Booking> getByHost(Long hostId) {
        return bookingRepository.findByHostId(hostId);
    }


    public List<BookingResponseDTO> getUpcomingBookings() {

        List<Booking> bookings = bookingRepository.findByStartDateAfter(LocalDate.now());

        return bookings.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private BookingResponseDTO mapToResponse(Booking booking) {
        BookingResponseDTO responseDto = new BookingResponseDTO();
        responseDto.setId(booking.getId());
        responseDto.setGuestId(booking.getGuestId());
        responseDto.setHostId(booking.getHostId());
        responseDto.setRoomId(booking.getRoomId());
        responseDto.setStartDate(booking.getStartDate());
        responseDto.setEndDate(booking.getEndDate());
        responseDto.setStatus(booking.getStatus());

        return responseDto;
    }

}