package com.team.hotelbooking.dtos;

import com.team.hotelbooking.entities.Room;
import com.team.hotelbooking.entities.RoomType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

public record RoomDTO(
        Long hostId,
        int roomNumber,
        RoomType roomType,
        int capacity,
        int pricePerNight,
        List<String> roomFeatures
) {
    public static RoomDTO fromEntity(Room room){
        return new RoomDTO(
                room.getHostId(),
                room.getRoomNumber(),
                room.getRoomType(),
                room.getCapacity(),
                room.getPricePerNight(),
                List.copyOf(room.getRoomFeatures())
        );
    }

    public Room toEntity() {
        Room room = new Room();
        room.setHostId(this.hostId);
        room.setRoomNumber(this.roomNumber);
        room.setRoomType(this.roomType);
        room.setCapacity(this.capacity);
        room.setPricePerNight(this.pricePerNight);
        room.setRoomFeatures(this.roomFeatures);

        return room;
    }

}
