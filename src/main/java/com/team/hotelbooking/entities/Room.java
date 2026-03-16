package com.team.hotelbooking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Room {
    @Id
    @GeneratedValue
    private Long id;
    private Long hostId;

    private int roomNumber;
    private RoomType roomType;
    private int capacity;
    private int pricePerNight;
    private List<String> roomFeatures;

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", hostId=" + hostId +
                ", roomNumber=" + roomNumber +
                ", roomType=" + roomType +
                ", capacity=" + capacity +
                ", pricePerNight=" + pricePerNight +
                ", roomFeatures=" + roomFeatures +
                '}';
    }
}
