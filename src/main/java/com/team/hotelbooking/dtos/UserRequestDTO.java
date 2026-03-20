package com.team.hotelbooking.dtos;

import com.team.hotelbooking.additional.UserType;
import com.team.hotelbooking.entities.User;

public record UserRequestDTO(
    String name,
    String username,
    String email,
    String password,
    String userType
)
{
    public User toUser()
    {
        User u = new User();
        u.setName(this.name);
        u.setUsername(this.username);
        u.setPassword(this.password);
        u.setEmail(this.email);
        u.setUserType(UserType.valueOf(this.userType));
        return u;
    }
}
