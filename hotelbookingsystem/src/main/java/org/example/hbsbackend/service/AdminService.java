package org.example.hbsbackend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.dto.AdminDTO;
import org.example.hbsbackend.entity.Admin;
import org.example.hbsbackend.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    public List<AdminDTO> getAllAdmins() {
        List<AdminDTO> adminDTOS = new ArrayList<>();
        for(Admin admin : adminRepository.findAll()) {
            adminDTOS.add(AdminDTO.convertToDTO(admin));
        }
        return adminDTOS;
    }
    public AdminDTO getAdminById(Long id) {
        return AdminDTO.convertToDTO(adminRepository.findById(id).get());
    }
    public AdminDTO createAdmin(Admin admin) {
        return AdminDTO.convertToDTO(adminRepository.save(admin));
    }
    public AdminDTO updateAdmin(Long id, AdminDTO adminDTO) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());
        admin.setEmail(adminDTO.getEmail());
        return AdminDTO.convertToDTO(adminRepository.save(admin));
    }
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}
