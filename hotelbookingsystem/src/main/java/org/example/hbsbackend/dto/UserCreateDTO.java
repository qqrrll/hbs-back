package org.example.hbsbackend.dto;

import lombok.Data;
import org.example.hbsbackend.entity.User;

import jakarta.validation.constraints.*;

@Data
public class UserCreateDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Invalid phone number"
    )
    private String phone;

    @NotBlank
    @Size(min = 6)
    private String password;
}
