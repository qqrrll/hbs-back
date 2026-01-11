package org.example.hbsbackend.dto;

import lombok.Data;
import org.example.hbsbackend.entity.Hotel;

import java.util.List;

@Data
public class HotelDTO {

    private Long id;
    private String name;
    private String city;
    private String address;
    private int roomCount;
    private int stars;
    private double rating;

    private List<RoomDTO> roomDTOS;

    // фото
    private List<Long> photoIds;
    private Long mainPhotoId;

    public static HotelDTO convertToDTO(Hotel hotel) {

        HotelDTO dto = new HotelDTO();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setCity(hotel.getCity());
        dto.setAddress(hotel.getAddress());
        dto.setRoomCount(hotel.getRoomCount());
        dto.setStars(hotel.getStars());
        dto.setRating(hotel.getRating());

        dto.setRoomDTOS(
                hotel.getRooms()
                        .stream()
                        .map(RoomDTO::convertToDTO)
                        .toList()
        );

        // все фото
        dto.setPhotoIds(
                hotel.getPhotos()
                        .stream()
                        .map(photo -> photo.getId())
                        .toList()
        );

        // главное фото
        dto.setMainPhotoId(
                hotel.getPhotos()
                        .stream()
                        .filter(photo -> photo.isMainPhoto())
                        .map(photo -> photo.getId())
                        .findFirst()
                        .orElse(null)
        );

        return dto;
    }
}
