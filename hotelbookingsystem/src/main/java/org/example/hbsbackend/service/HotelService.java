package org.example.hbsbackend.service;

import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.dto.HotelCreateDTO;
import org.example.hbsbackend.dto.HotelDTO;
import org.example.hbsbackend.dto.RoomDTO;
import org.example.hbsbackend.entity.Hotel;
import org.example.hbsbackend.repository.HotelRepository;
import org.example.hbsbackend.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    // ===================== HOTELS =====================

    @Transactional(readOnly = true)
    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(HotelDTO::convertToDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public HotelDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        return HotelDTO.convertToDTO(hotel);
    }


    @Transactional
    public HotelDTO createHotel(HotelCreateDTO dto) {

        Hotel hotel = new Hotel();
        hotel.setName(dto.getName());
        hotel.setCity(dto.getCity());
        hotel.setAddress(dto.getAddress());
        hotel.setStars(dto.getStars());
        hotel.setRating(0);
        hotel.setRoomCount(0);

        return HotelDTO.convertToDTO(hotelRepository.save(hotel));
    }

    @Transactional
    public HotelDTO updateHotel(Long id, HotelDTO dto) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        hotel.setName(dto.getName());
        hotel.setCity(dto.getCity());
        hotel.setAddress(dto.getAddress());
        hotel.setStars(dto.getStars());
        hotel.setRating(dto.getRating());

        return HotelDTO.convertToDTO(hotelRepository.save(hotel));
    }

    public void deleteHotel(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new RuntimeException("Hotel not found");
        }
        hotelRepository.deleteById(id);
    }

    // ===================== ROOMS =====================

    public List<RoomDTO> getFreeRooms(Long hotelId) {

        checkHotelExists(hotelId);

        return roomRepository
                .findByHotelIdAndOccupiedFalse(hotelId)
                .stream()
                .map(RoomDTO::convertToDTO)
                .toList();
    }

    public List<RoomDTO> getOccupiedRooms(Long hotelId) {

        checkHotelExists(hotelId);

        return roomRepository
                .findByHotelIdAndOccupiedTrue(hotelId)
                .stream()
                .map(RoomDTO::convertToDTO)
                .toList();
    }

    // ===================== HELPERS =====================

    private void checkHotelExists(Long hotelId) {
        if (!hotelRepository.existsById(hotelId)) {
            throw new RuntimeException("Hotel not found");
        }
    }
}
