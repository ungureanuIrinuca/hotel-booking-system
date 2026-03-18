package com.team.hotelbooking.services;

import com.team.hotelbooking.additional.RoomType;
import com.team.hotelbooking.dtos.RoomDTO;
import com.team.hotelbooking.entities.Room;
import com.team.hotelbooking.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRooms(RoomType roomType) {
        if (roomType != null) {
            return roomRepository.findByRoomType(roomType);
        }

        return roomRepository.findAll();
    }

    public RoomDTO getRoomByID(Long id) {
        Room roomById = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room with id " + id + " not found"));
        return RoomDTO.fromEntity(roomById);

    }

    public void addRoom(RoomDTO roomDTO) {
        Room roomEntity = roomDTO.toEntity();
        roomRepository.save(roomEntity);
    }

    public void updateRoom(Long id, RoomDTO roomDTO) {
        Room RoomEntity = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room with id " + id + " not found"));

        RoomEntity.setHost(roomDTO.Host());
        RoomEntity.setRoomNumber(roomDTO.roomNumber());
        RoomEntity.setRoomType(roomDTO.roomType());
        RoomEntity.setCapacity(roomDTO.capacity());
        RoomEntity.setPricePerNight(roomDTO.pricePerNight());
        RoomEntity.setRoomFeatures(roomDTO.roomFeatures());

        roomRepository.save(RoomEntity);


    }

    public void deleteRoom(Long id) {
        Room RoomById = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room with id " + id + " not found"));

        roomRepository.delete(RoomById);
    }
}
