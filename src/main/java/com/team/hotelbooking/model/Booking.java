package com.team.hotelbooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    public enum BookingStatus {
        ACTIVE,
        CANCELLED
    }

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    private Long hostId;
    private Long guestId;
    private Long roomId;

    private BookingStatus status;
}