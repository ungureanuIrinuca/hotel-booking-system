package com.team.hotelbooking.services;

import com.team.hotelbooking.additional.UserType;
import com.team.hotelbooking.dtos.LoginRequestDTO;
import com.team.hotelbooking.dtos.UserRequestDTO;
import com.team.hotelbooking.dtos.UserResponseDTO;
import com.team.hotelbooking.entities.User;
import com.team.hotelbooking.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    SecretKey secretKey;

    public UserResponseDTO createUser(UserRequestDTO d) throws Exception
    {
        if (UserType.valueOf(d.userType()) == UserType.ADMIN)
        {
            Jwt loginToken = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!loginToken.getClaim("user_type").equals("ADMIN"))
                throw new Exception("You cannot enroll yourself as admin!");
        }

        User u = d.toUser();
        u.setPassword(encoder.encode(u.getPassword()));
        repository.save(u);

        return UserResponseDTO.basicInfo(u);
    }
    public String login(LoginRequestDTO l) throws Exception
    {
        Optional<User> u=repository.findByUsername(l.username());
        if (
                u.isPresent()&& encoder.matches(l.password(), u.get().getPassword()))
        {
            User a=u.get();
            return Jwts.builder()
                    .setSubject(l.username())
                    .claim("id", a.getId())
                    .claim("user_type", a.getUserType().toString())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 900000))
                    .signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();
        }

        else
            throw new RuntimeException("Invalid Credentials");
    }

    public UserResponseDTO updateUser(Long id,UserRequestDTO d)
    {
        Optional<User> u=repository.findById(id);
        if (u.isPresent())
        {
            User user=u.get();
            if (d.username()!=null)
                user.setUsername(d.username());
            if (d.name()!=null)
                user.setName(d.username());
            if (d.userType()!=null)
                user.setUserType(UserType.valueOf(d.userType()));
            if (d.email()!=null)
                user.setEmail(d.email());
            if (d.password()!=null)
                user.setPassword(encoder.encode(d.password()));

            repository.save(user);
            return UserResponseDTO.basicInfo(user);
        }
        else{
            throw new RuntimeException("id not found");
        }
    }
    public UserResponseDTO getUser(Long id)
    {
        Optional<User> u=repository.findById(id);
        if (u.isPresent())
        {
            Jwt loginToken = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (id==loginToken.getClaim("id"))
                return UserResponseDTO.allInfo(u.get());
            else
                return UserResponseDTO.basicInfo(u.get());
        }
        else{
            throw new RuntimeException("id not found");
        }
    }
    public List<UserResponseDTO> getAllUsers()
    {
        List<User> l=repository.findAll();
        return l.stream().map(UserResponseDTO::basicInfo).toList();
    }
    public UserResponseDTO deleteUser(Long id)
    {
        Optional<User> u=repository.findById(id);
        if (u.isPresent())
        {
            UserResponseDTO d=UserResponseDTO.basicInfo(u.get());
            repository.delete(u.get());
            return d;
        }
        else{
            throw new RuntimeException("ID not found");
        }
    }
}
