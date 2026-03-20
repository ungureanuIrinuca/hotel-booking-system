package com.team.hotelbooking.controllers;


import com.team.hotelbooking.additional.RoomType;
import com.team.hotelbooking.dtos.RoomDTO;
import com.team.hotelbooking.entities.Room;
import com.team.hotelbooking.services.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/rooms")
public class RoomController {

    private final RoomService roomService;


    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @GetMapping
    public List<Room> getRooms(@RequestParam(required = false) RoomType roomType) {
        return roomService.getRooms(roomType);
    }

    @GetMapping("/{id}")
    public RoomDTO getRoomByID(@PathVariable Long id) {
        return roomService.getRoomByID(id);
    }

    @PostMapping
    public void registerNewRoom(@RequestBody RoomDTO RoomDTO) {
        roomService.addRoom(RoomDTO);
    }
    @PutMapping("/{id}")
    public void updateRoom(@PathVariable Long id, @RequestBody RoomDTO RoomDTO){
        roomService.updateRoom(id, RoomDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id){
        roomService.deleteRoom(id);
    }


}
