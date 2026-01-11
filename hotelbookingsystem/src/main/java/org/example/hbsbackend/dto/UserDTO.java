package org.example.hbsbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.example.hbsbackend.entity.Booking;
import org.example.hbsbackend.entity.Review;
import org.example.hbsbackend.entity.Role;
import org.example.hbsbackend.entity.User;

import java.util.ArrayList;
import java.util.List;
@Data
public class UserDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;
    @JsonIgnore
    private List<BookingDTO> bookingsDTOS;
    @JsonIgnore
    private List<ReviewDTO> reviewsDTOS;

    public static UserDTO convertToDTO(User user) {
        if (user == null) {
            return null; // или бросить исключение, в зависимости от логики приложения
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());

        // Обработка bookings
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        if (user.getBookings() != null) {
            for (Booking booking : user.getBookings()) {
                bookingDTOS.add(BookingDTO.convertToDTO(booking));
            }
        }
        // Если список был null — остаётся пустым
        userDTO.setBookingsDTOS(bookingDTOS);

        // Обработка reviews
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        if (user.getReviews() != null) {
            for (Review review : user.getReviews()) {
                reviewDTOS.add(ReviewDTO.convertToDTO(review));
            }
        }
        // Если список был null — остаётся пустым
        userDTO.setReviewsDTOS(reviewDTOS);

        System.out.println(bookingDTOS);
        System.out.println(reviewDTOS);

        return userDTO;
    }
}
