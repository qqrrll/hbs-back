package org.example.hbsbackend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.hbsbackend.entity.RoomLevel;

@Data
public class RoomCreateDTO {

    @NotBlank
    private String number;

    @NotNull
    private RoomLevel level;

    @Min(1)
    private int beds;

    @NotNull
    private Long hotelId;

    @Positive
    private double pricePerNight;

    private boolean occupied;
}

