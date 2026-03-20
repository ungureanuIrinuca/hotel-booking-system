package com.team.hotelbooking.dtos;
import com.team.hotelbooking.entities.Booking;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingResponseDTO {
    private Long id;
    private Long guestId;
    private Long roomId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public static BookingResponseDTO mapToDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();

        dto.setId(booking.getId());
        dto.setGuestId(booking.getGuest().getId());
        dto.setRoomId(booking.getRoom().getId());
        dto.setStartDate(booking.getStartDate());
        dto.setEndDate(booking.getEndDate());
        dto.setStatus(booking.getStatus().name());

        return dto;
    }
}