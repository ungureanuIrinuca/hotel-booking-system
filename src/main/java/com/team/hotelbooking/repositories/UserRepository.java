package com.team.hotelbooking.repositories;

import com.team.hotelbooking.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //Example methods
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

}
