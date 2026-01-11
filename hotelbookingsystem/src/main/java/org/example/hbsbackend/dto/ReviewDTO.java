package org.example.hbsbackend.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.example.hbsbackend.entity.Hotel;
import org.example.hbsbackend.entity.Review;
import org.example.hbsbackend.entity.User;
@Data
public class ReviewDTO {

    private Long id;

    private UserShortDTO user;
    private HotelShortDTO hotel;

    private int rating;
    private String text;

    public static ReviewDTO convertToDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setText(review.getText());

        dto.setUser(UserShortDTO.convertToDTO(review.getUser()));
        dto.setHotel(HotelShortDTO.convertToDTO(review.getHotel()));

        return dto;
    }
}

