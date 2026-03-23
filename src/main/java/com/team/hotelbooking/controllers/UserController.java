package com.team.hotelbooking.controllers;

import com.team.hotelbooking.dtos.LoginRequestDTO;
import com.team.hotelbooking.dtos.UserRequestDTO;
import com.team.hotelbooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO l) throws Exception {
        return new ResponseEntity<>(service.login(l), HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserRequestDTO dto) throws Exception {
        return new ResponseEntity<>(service.createUser(dto), HttpStatus.CREATED);
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserRequestDTO u,
                                        @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(service.updateUser(id, u), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id)
    {
        return new ResponseEntity<>(service.getUser(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getAllUsers()
    {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id)
    {
        return new ResponseEntity<>(service.deleteUser(id), HttpStatus.OK);
    }
}
