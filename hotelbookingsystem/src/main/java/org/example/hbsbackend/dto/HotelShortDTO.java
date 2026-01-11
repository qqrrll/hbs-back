package org.example.hbsbackend.dto;

import lombok.Data;
import org.example.hbsbackend.entity.Hotel;

@Data
public class HotelShortDTO {
    private Long id;
    private String name;
    private int stars;
    private String city;

    public static HotelShortDTO convertToDTO(Hotel hotel) {
        HotelShortDTO dto = new HotelShortDTO();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setStars(hotel.getStars());
        dto.setCity(hotel.getCity());
        return dto;
    }
}
