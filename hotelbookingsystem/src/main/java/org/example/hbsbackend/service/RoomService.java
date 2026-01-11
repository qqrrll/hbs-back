package org.example.hbsbackend.service;

import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.dto.RoomCreateDTO;
import org.example.hbsbackend.dto.RoomDTO;
import org.example.hbsbackend.entity.Hotel;
import org.example.hbsbackend.entity.Room;
import org.example.hbsbackend.repository.HotelRepository;
import org.example.hbsbackend.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public List<RoomDTO> getAllRooms() {
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for(Room room : roomRepository.findAll()) {
            roomDTOS.add(RoomDTO.convertToDTO(room));
        }
        return roomDTOS;
    }
    public RoomDTO getRoomById(Long id) {
        return RoomDTO.convertToDTO(roomRepository.findById(id).get());
    }
    public RoomDTO createRoom(RoomCreateDTO roomDTO) {
        Hotel hotel = hotelRepository.findById(roomDTO.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        Room room = new Room();
        room.setHotel(hotel);
        room.setNumber(roomDTO.getNumber());
        room.setPricePerNight(roomDTO.getPricePerNight());
        room.setOccupied(roomDTO.isOccupied());
        room.setLevel(roomDTO.getLevel());
        room.setBeds(roomDTO.getBeds());
        return RoomDTO.convertToDTO(roomRepository.save(room));
    }

    public RoomDTO updateRoom(Long id, RoomDTO roomDTO) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        room.setOccupied(roomDTO.isOccupied());
        room.setNumber(roomDTO.getNumber());
        room.setLevel(roomDTO.getLevel());
        room.setBeds(roomDTO.getBeds());
        room.setPricePerNight(roomDTO.getPricePerNight());
        return RoomDTO.convertToDTO(roomRepository.save(room));
    }
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

}
