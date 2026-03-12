package com.team.hotelbooking.entities;

import com.team.hotelbooking.additional.RoomType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

@Entity
@Table
public class Room {
    @Id
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"
    )
    private Long id;
    private String roomNumber;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;

    private RoomType roomType;
    private BigDecimal pricePerNight;
    private int capacity;

    @ElementCollection
    @CollectionTable(name = "room_features", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "feature")
    private List<String> roomFeatures;

    public Room(String roomNumber, User host, RoomType roomType, BigDecimal pricePerNight, int capacity, List<String> roomFeatures) {
        this.roomNumber = roomNumber;
        this.host = host;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.roomFeatures = roomFeatures;
    }

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
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

}
