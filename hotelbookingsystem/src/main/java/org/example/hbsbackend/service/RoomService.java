package org.example.hbsbackend.service;

import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.dto.RoomCreateDTO;
import org.example.hbsbackend.dto.RoomDTO;
import org.example.hbsbackend.entity.Hotel;
import org.example.hbsbackend.entity.Room;
import org.example.hbsbackend.repository.HotelRepository;
import org.example.hbsbackend.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @Transactional(readOnly = true)
    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAllSafe()
                .stream()
                .map(RoomDTO::convertToDTO)
                .toList();
    }

    public RoomDTO getRoomById(Long id) {
        return RoomDTO.convertToDTO(
                roomRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Room not found"))
        );
    }

    public RoomDTO createRoom(RoomCreateDTO dto) {
        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        Room room = new Room();
        room.setHotel(hotel);
        room.setNumber(dto.getNumber());
        room.setPricePerNight(dto.getPricePerNight());
        room.setOccupied(dto.isOccupied());
        room.setLevel(dto.getLevel());
        room.setBeds(dto.getBeds());

        return RoomDTO.convertToDTO(roomRepository.save(room));
    }

    public RoomDTO updateRoom(Long id, RoomDTO dto) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        room.setOccupied(dto.isOccupied());
        room.setNumber(dto.getNumber());
        room.setLevel(dto.getLevel());
        room.setBeds(dto.getBeds());
        room.setPricePerNight(dto.getPricePerNight());

        return RoomDTO.convertToDTO(roomRepository.save(room));
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}

