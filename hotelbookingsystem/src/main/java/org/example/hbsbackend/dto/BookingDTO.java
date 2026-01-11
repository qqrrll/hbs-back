package org.example.hbsbackend.dto;

import lombok.Data;
import org.example.hbsbackend.entity.Booking;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Data
public class BookingDTO {

    private Long id;

    private Long userId;
    private Long hotelId;

    private List<Long> roomIds;

    private int roomCount;
    private double totalPrice;

    private LocalDate startDate;
    private LocalDate endDate;
    public static BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setUserId(booking.getUser().getId());
        dto.setHotelId(booking.getHotel().getId());
        dto.setRoomCount(booking.getRoomCount());
        dto.setTotalPrice(booking.getTotalPrice());
        dto.setStartDate(booking.getStartDate());
        dto.setEndDate(booking.getEndDate());
        List<Long> roomIds = booking.getRooms()
                .stream()
                .map(room -> room.getId())
                .toList();
        dto.setRoomIds(roomIds);


        return dto;
    }
}

