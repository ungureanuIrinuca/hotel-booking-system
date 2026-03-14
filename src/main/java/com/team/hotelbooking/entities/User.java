package com.team.hotelbooking.entities;

import com.team.hotelbooking.additional.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    private List<Room> rooms;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    public User(String name, String username, String email, String password, UserType userType, List<Room> rooms, List<Booking> bookings) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.rooms = rooms;
        this.bookings = bookings;
    }
}