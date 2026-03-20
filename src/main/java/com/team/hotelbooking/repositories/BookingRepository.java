package com.team.hotelbooking.repositories;

import com.team.hotelbooking.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByRoomId(Long id);

    List<Booking> findByGuestId(Long id);

   // List<Booking> findByHostId(Long id);

    List<Booking> findByStartDateAfter(LocalDate date);

    List<Booking> findAll();

    @Query("SELECT DISTINCT b.room.id FROM Booking b")
    List<Long> findDistinctRoomIds();
}
