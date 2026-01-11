package org.example.hbsbackend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookingCreateDTO {

    @NotNull(message = "User id is required")
    private Long userId;

    @NotEmpty(message = "Room ids must not be empty")
    private List<Long> roomIds;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or later")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;
}
