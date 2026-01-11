package org.example.hbsbackend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class ReviewCreateDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long hotelId;

    @Min(1)
    @Max(5)
    private int rating;

    @NotBlank
    @Size(max = 1000)
    private String text;
}
