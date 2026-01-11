package org.example.hbsbackend.dto;

import lombok.Data;
import org.example.hbsbackend.entity.Admin;
import org.example.hbsbackend.entity.User;

@Data
public class AdminDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;
    public static AdminDTO convertToDTO(Admin admin) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setFirstName(admin.getFirstName());
        adminDTO.setLastName(admin.getLastName());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setPhone(admin.getPhone());
        return adminDTO;
    }
    public static Admin convertToEntity(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setId(adminDTO.getId());
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());
        admin.setEmail(adminDTO.getEmail());
        admin.setPhone(adminDTO.getPhone());
        return admin;
    }
}
