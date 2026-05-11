package com.ticketflow.FullStack_back.controllers;

import com.ticketflow.FullStack_back.models.LoginRequest;
import com.ticketflow.FullStack_back.models.LoginResponse;
import com.ticketflow.FullStack_back.models.User;
import com.ticketflow.FullStack_back.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody User user) {
        LoginResponse response = authService.registerUser(user);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/payment")
    public ResponseEntity<LoginResponse> processPayment(@RequestBody Object paymentData) {
        // Simular procesamiento de pago exitoso
        LoginResponse response = new LoginResponse(true, "Pago procesado exitosamente", null, null);
        return ResponseEntity.ok(response);
    }
}
