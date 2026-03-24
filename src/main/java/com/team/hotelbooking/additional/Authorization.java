package com.team.hotelbooking.additional;

import com.team.hotelbooking.repositories.BookingRepository;
import com.team.hotelbooking.repositories.RoomRepository;
import com.team.hotelbooking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component("security")
public class Authorization {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RoomRepository roomRepository;
    public boolean isAdmin()
    {
        Jwt loginToken = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loginToken.getClaim("user_type").equals("ADMIN");
    }

    public long getAuthId()
    {
        Jwt loginToken = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loginToken.getClaim("id");
    }

    public boolean isGuestForUser(long user_id)
    {
        long logged_id=getAuthId();
        return bookingRepository.findAll()
                                .stream()
                                .anyMatch(b->b.getGuest().getId()==user_id &&
                                        b.getRoom().getHost().getId()==logged_id);
    }

    public boolean isHostForUSer(long user_id)
    {
        long logged_id=getAuthId();
        return bookingRepository.findAll()
                .stream()
                .anyMatch(b->b.getGuest().getId()==logged_id &&
                        b.getRoom().getHost().getId()==user_id);
    }

    public boolean canSeeUser(long user_id)
    {
        return (getAuthId()==user_id || isAdmin() || isGuestForUser(user_id) || isHostForUSer(user_id));
    }

    public boolean ownsRoom(long room_id)
    {
        return roomRepository.findByHostId(getAuthId())
                .stream()
                .anyMatch(room -> room_id==room.getId());
    }

    public boolean isBookingHost(long booking_id)
    {
        long logged_Id=getAuthId();
        return bookingRepository.findAll()
                .stream()
                .anyMatch(b->b.getId()==booking_id
                        && logged_Id == b.getRoom().getHost().getId());
    }

    public boolean isBookingGuest(long booking_id)
    {
        long logged_Id=getAuthId();
        return bookingRepository.findAll()
                .stream().
                anyMatch(b->b.getId()==booking_id
                && logged_Id == b.getGuest().getId());
    }

    public boolean canSeeBooking(long booking_id)
    {
        return (isAdmin() || isBookingGuest(booking_id) || isBookingHost(booking_id));
    }
}
