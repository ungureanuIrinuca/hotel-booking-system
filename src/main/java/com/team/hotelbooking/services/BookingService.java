package com.team.hotelbooking.services;

import com.team.hotelbooking.additional.BookingStatus;
import com.team.hotelbooking.dtos.BookingRequestDTO;
import com.team.hotelbooking.dtos.BookingResponseDTO;
import com.team.hotelbooking.entities.Booking;
import com.team.hotelbooking.entities.Room;
import com.team.hotelbooking.entities.User;
import com.team.hotelbooking.repositories.BookingRepository;
import com.team.hotelbooking.repositories.RoomRepository;
import com.team.hotelbooking.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;

    }

    public BookingResponseDTO createBooking(BookingRequestDTO request) {
        Long roomId = request.getRoomId();
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();

        validateDates(startDate, endDate);

        if (!checkAvailability(roomId, startDate, endDate))
            throw new IllegalArgumentException("Room with id " + roomId + " is not available.");

        User guest;
        guest = userRepository.findById(request.getGuestId()).orElseThrow();
        Room room = roomRepository.findById(request.getRoomId()).orElseThrow();

        Booking booking = new Booking();
        booking.setGuest(guest);
        booking.setRoom(room);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);

        booking.setStatus(BookingStatus.ACTIVE);

        Booking saved = bookingRepository.save(booking);
        return BookingResponseDTO.mapToDTO(saved);
    }

    private boolean checkAvailability(Long roomId, LocalDate startDate, LocalDate endDate) {

        List<Booking> roomBookings = bookingRepository.findByRoomId(roomId);

        for (Booking b : roomBookings) {
            boolean overlap =
                    startDate.isBefore(b.getEndDate()) &&
                            endDate.isAfter(b.getStartDate());

            if (overlap && b.getStatus() == BookingStatus.ACTIVE)
                return false;
        }
        return true;


    }

    public List<Long> getAvailableRooms(LocalDate startDate, LocalDate endDate) {

        List<Long> roomIds = bookingRepository.findDistinctRoomIds();
        return roomIds.stream()
                .filter(roomId -> checkAvailability(roomId, startDate, endDate))
                .toList();


    }

    public void cancelBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking with id " + id + " does not exist."));

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);


    }

    public List<BookingResponseDTO> getByGuest(Long guestId) {
        return bookingRepository.findByGuestId(guestId)
                .stream()
                .map(BookingResponseDTO::mapToDTO)
                .toList();
    }

    public List<BookingResponseDTO> getByHost(Long hostId) {

        List<Booking> bookings = bookingRepository.findByRoom_Host_Id(hostId);
        return bookings.stream()
                .map(BookingResponseDTO::mapToDTO)
                .toList();

    }


    public List<BookingResponseDTO> getUpcomingBookings() {

        List<Booking> bookings = bookingRepository.findByStartDateAfter(LocalDate.now());

        return bookings.stream()
                .map(BookingResponseDTO::mapToDTO)
                .toList();
    }


    private void validateDates(LocalDate startDate, LocalDate endDate) {
        if (!startDate.isBefore(endDate))
            throw new IllegalArgumentException("Invalid date interval.");

        if (startDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Booking cannot start in the past.");
    }

}