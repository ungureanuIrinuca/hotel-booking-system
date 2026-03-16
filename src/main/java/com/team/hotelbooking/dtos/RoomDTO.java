package com.team.hotelbooking.dtos;

import com.team.hotelbooking.entities.Room;
import com.team.hotelbooking.additional.RoomType;
import com.team.hotelbooking.entities.User;


import java.math.BigDecimal;
import java.util.List;

public record RoomDTO(
        User Host,
        String roomNumber,
        RoomType roomType,
        int capacity,
        BigDecimal pricePerNight,
        List<String> roomFeatures
) {
    public static RoomDTO fromEntity(Room room){
        return new RoomDTO(
                room.getHost(),
                room.getRoomNumber(),
                room.getRoomType(),
                room.getCapacity(),
                room.getPricePerNight(),
                List.copyOf(room.getRoomFeatures())
        );
    }

    public Room toEntity() {
        Room room = new Room();
        room.setHost(this.Host);
        room.setRoomNumber(this.roomNumber);
        room.setRoomType(this.roomType);
        room.setCapacity(this.capacity);
        room.setPricePerNight(this.pricePerNight);
        room.setRoomFeatures(this.roomFeatures);

        return room;
    }

}
