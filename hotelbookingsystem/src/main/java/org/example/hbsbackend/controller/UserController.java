package org.example.hbsbackend.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.dto.UserCreateDTO;
import org.example.hbsbackend.dto.UserDTO;
import org.example.hbsbackend.entity.Role;
import org.example.hbsbackend.entity.User;
import org.example.hbsbackend.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/get-all-users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/get-user-by-id/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-user")
    public UserDTO createUser(@RequestBody UserCreateDTO dto) {
        return userService.createUser(dto);
    }
    @PutMapping("/update-user/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{id}/role")
    public void changeRole(
            @PathVariable Long id,
            @RequestParam Role role
    ) {
        userService.changeRole(id, role);
    }


}
