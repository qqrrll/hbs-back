package org.example.hbsbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.dto.RoomCreateDTO;
import org.example.hbsbackend.dto.RoomDTO;
import org.example.hbsbackend.service.RoomService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/room")
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/get-all-rooms")
    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/get-room-by-id/{id}")
    public RoomDTO getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @PostMapping("/create-room")
    @PreAuthorize("hasRole('ADMIN')")
    public RoomDTO createRoom(@RequestBody RoomCreateDTO roomDTO) {
        return roomService.createRoom(roomDTO);
    }

    @PutMapping("update-room/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RoomDTO updateRoom(@PathVariable Long id, @RequestBody RoomDTO roomDTO) {
        return roomService.updateRoom(id, roomDTO);
    }

    @DeleteMapping("delete-room/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);

    }
}
