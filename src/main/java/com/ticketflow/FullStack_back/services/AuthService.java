package com.ticketflow.FullStack_back.services;

import com.ticketflow.FullStack_back.dto.auth.LoginRequest;
import com.ticketflow.FullStack_back.dto.auth.LoginResponse;
import com.ticketflow.FullStack_back.dto.auth.RegisterRequest;
import com.ticketflow.FullStack_back.dto.user.UserResponse;
import com.ticketflow.FullStack_back.mappers.UserMapper;
import com.ticketflow.FullStack_back.models.Role;
import com.ticketflow.FullStack_back.models.User;
import com.ticketflow.FullStack_back.repositories.RoleRepository;
import com.ticketflow.FullStack_back.repositories.UserRepository;
import com.ticketflow.FullStack_back.shared.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPwdHash())) {
            UserResponse userResponse = userMapper.toResponse(user);
            return new LoginResponse(true, "Login exitoso", userResponse, user.getRole().getName());
        }

        return new LoginResponse(false, "Email o contraseña incorrectos", null, null);
    }

    @Transactional
    public LoginResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new LoginResponse(false, "El email ya está registrado", null, null);
        }

        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPwdHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        userRepository.save(user);

        UserResponse userResponse = userMapper.toResponse(user);
        return new LoginResponse(true, "Usuario registrado exitosamente", userResponse, role.getName());
    }
}
