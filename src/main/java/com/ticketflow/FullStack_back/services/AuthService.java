package com.ticketflow.FullStack_back.services;

import com.ticketflow.FullStack_back.models.LoginRequest;
import com.ticketflow.FullStack_back.models.LoginResponse;
import com.ticketflow.FullStack_back.models.User;
import com.ticketflow.FullStack_back.data.DataStore;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public LoginResponse login(LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        // Verificar usuario
        User user = DataStore.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return new LoginResponse(true, "Login exitoso", user, "USER");
        }

        // Verificar admin
        User admin = DataStore.getAdminByEmail(email);
        if (admin != null && admin.getPassword().equals(password)) {
            return new LoginResponse(true, "Login exitoso", admin, "ADMIN");
        }

        return new LoginResponse(false, "Email o contraseña incorrectos", null, null);
    }

    public LoginResponse registerUser(User user) {
        // Verificar que el email no exista
        if (DataStore.getUserByEmail(user.getEmail()) != null) {
            return new LoginResponse(false, "El email ya está registrado", null, null);
        }

        user.setRole("USER");
        DataStore.saveUser(user);
        return new LoginResponse(true, "Usuario registrado exitosamente", user, "USER");
    }
}
