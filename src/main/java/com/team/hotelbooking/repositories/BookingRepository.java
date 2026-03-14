package com.team.hotelbooking.repositories;

import com.team.hotelbooking.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    //Example methods
    List<Booking> findByGuestId(Long guestId);
    List<Booking> findByRoomId(Long roomId);
}
