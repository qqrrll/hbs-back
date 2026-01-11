package org.example.hbsbackend.dto;

import lombok.Data;
import org.example.hbsbackend.entity.Room;
import org.example.hbsbackend.entity.RoomLevel;

import java.util.List;

@Data
public class RoomDTO {

    private Long id;
    private String number;
    private RoomLevel level;
    private int beds;
    private boolean occupied;
    private double pricePerNight;

    private HotelShortDTO hotel;
    private List<Long> photoIds;
    private Long mainPhotoId;

    public static RoomDTO convertToDTO(Room room) {

        RoomDTO dto = new RoomDTO();
        dto.setId(room.getId());
        dto.setNumber(room.getNumber());
        dto.setLevel(room.getLevel());
        dto.setBeds(room.getBeds());
        dto.setOccupied(room.isOccupied());
        dto.setPricePerNight(room.getPricePerNight());

        if (room.getHotel() != null) {
            dto.setHotel(HotelShortDTO.convertToDTO(room.getHotel()));
        }

        // все фото
        dto.setPhotoIds(
                room.getPhotos()
                        .stream()
                        .map(photo -> photo.getId())
                        .toList()
        );

        // главное фото
        dto.setMainPhotoId(
                room.getPhotos()
                        .stream()
                        .filter(photo -> photo.isMainPhoto())
                        .map(photo -> photo.getId())
                        .findFirst()
                        .orElse(null)
        );

        return dto;
    }
}
