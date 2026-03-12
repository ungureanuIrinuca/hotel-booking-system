package com.team.hotelbooking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;


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

    public Room(int pricePerNight, int roomNumber, Long id, Long hostId, RoomType roomType, int capacity, List<String> roomFeatures) {
        this.pricePerNight = pricePerNight;
        this.roomNumber = roomNumber;
        this.id = id;
        this.hostId = hostId;
        this.roomType = roomType;
        this.capacity = capacity;
        this.roomFeatures = roomFeatures;
    }

    public Room() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<String> getRoomFeatures() {
        return roomFeatures;
    }

    public void setRoomFeatures(List<String> roomFeatures) {
        this.roomFeatures = roomFeatures;
    }

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
