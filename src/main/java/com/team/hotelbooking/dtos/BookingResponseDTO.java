package com.team.hotelbooking.dtos;

import com.team.hotelbooking.model.Booking.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingResponseDTO {
    private Long id;
    private Long hostId;
    private Long guestId;
    private Long roomId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BookingStatus status;
}