package com.team.hotelbooking.entities;

import com.team.hotelbooking.additional.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "rooms")
@NoArgsConstructor
@Getter
@Setter
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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
}
