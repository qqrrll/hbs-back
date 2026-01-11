package org.example.hbsbackend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class HotelCreateDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String city;

    @NotBlank
    private String address;

    @Min(1)
    @Max(5)
    private int stars;
}
