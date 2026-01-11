package org.example.hbsbackend.dto;

import lombok.Data;
import org.example.hbsbackend.entity.User;

@Data
public class UserShortDTO {
    private Long id;
    private String firstName;

    public static UserShortDTO convertToDTO(User user) {
        UserShortDTO dto = new UserShortDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        return dto;
    }
}
