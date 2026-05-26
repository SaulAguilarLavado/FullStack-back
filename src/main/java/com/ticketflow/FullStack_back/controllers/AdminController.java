package com.ticketflow.FullStack_back.controllers;

import com.ticketflow.FullStack_back.models.User;
import com.ticketflow.FullStack_back.shared.response.ApiResponse;
import com.ticketflow.FullStack_back.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<Collection<User>>> getAllUsers() {
        Collection<User> users = adminService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse<>(true, "Usuarios encontrados", users));
    }

    @GetMapping("/admins")
    public ResponseEntity<ApiResponse<Collection<User>>> getAllAdmins() {
        Collection<User> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(new ApiResponse<>(true, "Admins encontrados", admins));
    }

    @DeleteMapping("/users/{email}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable String email) {
        adminService.deleteUser(email);
        return ResponseEntity.ok(new ApiResponse<>(true, "Usuario eliminado", email));
    }

    @PutMapping("/users")
    public ResponseEntity<ApiResponse<User>> updateUser(@RequestBody User user) {
        User updated = adminService.updateUser(user);
        return ResponseEntity.ok(new ApiResponse<>(true, "Usuario actualizado", updated));
    }
}
