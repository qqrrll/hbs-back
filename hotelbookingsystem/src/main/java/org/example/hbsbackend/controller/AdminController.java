package org.example.hbsbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.dto.AdminDTO;
import org.example.hbsbackend.entity.Admin;
import org.example.hbsbackend.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    @GetMapping("/get-all-admins")
    public List<AdminDTO> getAllAdmins() {
        return adminService.getAllAdmins();
    }
    @GetMapping("/get-admin-by-id/{id}")
    public AdminDTO getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }
    @PostMapping("/create-admin")
    public AdminDTO createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }
    @PutMapping("/update-admin/{id}")
    public AdminDTO updateAdmin(@PathVariable Long id , @RequestBody AdminDTO adminDTO) {
     return adminService.updateAdmin(id, adminDTO);
    }
    @DeleteMapping("/delete-admin/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }
}
