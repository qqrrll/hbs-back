package org.example.hbsbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.dto.HotelCreateDTO;
import org.example.hbsbackend.dto.HotelDTO;
import org.example.hbsbackend.dto.RoomDTO;
import org.example.hbsbackend.entity.Hotel;
import org.example.hbsbackend.service.HotelService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService hotelService;
    @GetMapping("/get-all-hotel")
    public List<HotelDTO> getAllHotel(){
        return hotelService.getAllHotels();
    }
    @GetMapping("/get-hotel-by-id/{id}")
    public HotelDTO getHotelById(@PathVariable Long id){
        return hotelService.getHotelById(id);
    }
    @PostMapping("/create-hotel")
    @PreAuthorize("hasRole('ADMIN')")
    public HotelDTO createHotel(@RequestBody HotelCreateDTO hotel){
        return hotelService.createHotel(hotel);
    }
    @PutMapping("/update-hotel/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public HotelDTO updateHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDTO){
        return hotelService.updateHotel(id, hotelDTO);
    }
    @DeleteMapping("/delete-hotel/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteHotel(@PathVariable Long id){
        hotelService.deleteHotel(id);
    }
    @GetMapping("/{hotelId}/rooms/free")
    public List<RoomDTO> getFreeRooms(@PathVariable Long hotelId) {
        return hotelService.getFreeRooms(hotelId);
    }

    @GetMapping("/{hotelId}/rooms/occupied")
    public List<RoomDTO> getOccupiedRooms(@PathVariable Long hotelId) {
        return hotelService.getOccupiedRooms(hotelId);
    }

}


