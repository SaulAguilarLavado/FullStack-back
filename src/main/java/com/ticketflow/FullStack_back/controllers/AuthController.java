package com.ticketflow.FullStack_back.controllers;

import com.ticketflow.FullStack_back.dto.auth.LoginRequest;
import com.ticketflow.FullStack_back.dto.auth.LoginResponse;
import com.ticketflow.FullStack_back.dto.auth.RegisterRequest;
import com.ticketflow.FullStack_back.services.AuthService;
import com.ticketflow.FullStack_back.shared.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status)
                .body(new ApiResponse<>(response.isSuccess(), response.getMessage(), response));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<LoginResponse>> register(@Valid @RequestBody RegisterRequest request) {
        LoginResponse response = authService.register(request);
        HttpStatus status = response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status)
                .body(new ApiResponse<>(response.isSuccess(), response.getMessage(), response));
    }
}
