package com.team.hotelbooking.repositories;

import com.team.hotelbooking.additional.RoomType;
import com.team.hotelbooking.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    //Example methods
    List<Room> findByHostId(Long hostId);
    List<Room> findByRoomType(RoomType roomType);
}
