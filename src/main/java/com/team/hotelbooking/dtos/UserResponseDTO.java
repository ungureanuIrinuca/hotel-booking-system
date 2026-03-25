package com.team.hotelbooking.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.team.hotelbooking.additional.UserType;
import com.team.hotelbooking.entities.Booking;
import com.team.hotelbooking.entities.Room;
import com.team.hotelbooking.entities.User;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponseDTO (
        String name,
        String username,
        String email,
        Long id,
        UserType type,
        List<Long> room_ids,
        List<Long> own_booking_ids
    )
{
    public static UserResponseDTO basicInfo(User u){
        return new UserResponseDTO(
                u.getName(),
                u.getUsername(),
                u.getEmail(),
                u.getId(),
                u.getUserType(),
                null,
                null
        );
    }
    public static UserResponseDTO allInfo(User u){
        List<Long> own_booking_ids = u.getBookings()
                                        .stream()
                                        .filter(b-> Objects.equals(b.getGuest().getId(), u.getId()))
                                        .map(Booking::getId)
                                        .toList();
        List<Long> room_ids;
        if (u.getUserType()== UserType.HOST)
        {
            room_ids = u.getRooms()
                    .stream()
                    .filter(r-> Objects.equals(r.getHost().getId(), u.getId()))
                    .map(Room::getId)
                    .toList();
        } else {
            room_ids = null;
        }
        return new UserResponseDTO(
                u.getName(),
                u.getUsername(),
                u.getEmail(),
                u.getId(),
                u.getUserType(),
                room_ids,
                own_booking_ids
        );
    }
}
