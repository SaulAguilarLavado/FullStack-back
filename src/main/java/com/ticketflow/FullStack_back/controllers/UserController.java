package com.ticketflow.FullStack_back.controllers;

import com.ticketflow.FullStack_back.dto.user.PasswordChangeRequest;
import com.ticketflow.FullStack_back.dto.user.UserRequest;
import com.ticketflow.FullStack_back.dto.user.UserResponse;
import com.ticketflow.FullStack_back.dto.user.UserUpdateRequest;
import com.ticketflow.FullStack_back.shared.response.ApiResponse;
import com.ticketflow.FullStack_back.services.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<UserResponse>>> getUsers(Pageable pageable) {
        Page<UserResponse> page = userService.getUsers(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Usuarios encontrados", page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable String id) {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Usuario encontrado", response));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody UserRequest request) {
        UserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Usuario creado", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable String id, @Valid @RequestBody UserUpdateRequest request) {
        UserResponse response = userService.updateUser(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Usuario actualizado", response));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<ApiResponse<UserResponse>> changePassword(@PathVariable String id, @Valid @RequestBody PasswordChangeRequest request) {
        UserResponse response = userService.changePassword(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Contraseña actualizada", response));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<UserResponse>> deactivateUser(@PathVariable String id) {
        UserResponse response = userService.deactivateUser(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Usuario desactivado", response));
    }
}
