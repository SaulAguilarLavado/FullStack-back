package com.ticketflow.FullStack_back.controllers;

import com.ticketflow.FullStack_back.models.User;
import com.ticketflow.FullStack_back.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<Collection<User>> getAllUsers() {
        Collection<User> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/events")
    public ResponseEntity<String> getAllEvents() {
        // Retornar lista de eventos (cuando se implemente)
        String events = "[{\"id\": 1, \"title\": \"Evento de ejemplo\", \"type\": \"concierto\"}]";
        return ResponseEntity.ok(events);
    }

    @DeleteMapping("/users/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        boolean deleted = adminService.deleteUser(email);
        if (deleted) {
            return ResponseEntity.ok("{\"message\": \"Usuario eliminado\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"message\": \"Usuario no encontrado\"}");
        }
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updated = adminService.updateUser(user);
        return ResponseEntity.ok(updated);
    }
}
