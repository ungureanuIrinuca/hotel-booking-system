package com.team.hotelbooking.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingRequestDTO {
    private Long hostId;
    private Long guestId;
    private Long roomId;
    private LocalDate startDate;
    private LocalDate endDate;
}