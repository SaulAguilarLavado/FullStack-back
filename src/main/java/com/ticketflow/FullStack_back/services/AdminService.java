package com.ticketflow.FullStack_back.services;

import com.ticketflow.FullStack_back.models.User;
import com.ticketflow.FullStack_back.repositories.UserRepository;
import com.ticketflow.FullStack_back.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AdminService {
    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Collection<User> getAllAdmins() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole() != null && "ADMIN".equalsIgnoreCase(user.getRole().getName()))
                .toList();
    }

    public boolean deleteUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        userRepository.delete(user);
        return true;
    }

    public User updateUser(User user) {
        User existing = userRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        existing.setFirstName(user.getFirstName());
        existing.setLastName(user.getLastName());
        existing.setPhone(user.getPhone());
        existing.setRole(user.getRole());
        existing.setEmail(user.getEmail());
        userRepository.save(existing);
        return existing;
    }
}
