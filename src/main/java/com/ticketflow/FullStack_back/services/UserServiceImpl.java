package com.ticketflow.FullStack_back.services;

import com.ticketflow.FullStack_back.dto.user.PasswordChangeRequest;
import com.ticketflow.FullStack_back.dto.user.UserRequest;
import com.ticketflow.FullStack_back.dto.user.UserResponse;
import com.ticketflow.FullStack_back.dto.user.UserUpdateRequest;
import com.ticketflow.FullStack_back.mappers.UserMapper;
import com.ticketflow.FullStack_back.models.Role;
import com.ticketflow.FullStack_back.models.User;
import com.ticketflow.FullStack_back.repositories.RoleRepository;
import com.ticketflow.FullStack_back.repositories.UserRepository;
import com.ticketflow.FullStack_back.shared.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<UserResponse> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toResponse);
    }

    @Override
    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DataIntegrityViolationException("El email ya está registrado");
        }
        Role role = roleRepository.findByName(request.getRole() != null ? request.getRole() : "USER")
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
        User user = userMapper.toEntity(request);
        user.setRole(role);
        user.setPwdHash(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setActive(request.getActive());
        if (request.getRole() != null) {
            Role role = roleRepository.findByName(request.getRole())
                    .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
            user.setRole(role);
        }
        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional
    public UserResponse changePassword(String id, PasswordChangeRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPwdHash())) {
            throw new DataIntegrityViolationException("Contraseña actual incorrecta");
        }
        user.setPwdHash(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional
    public UserResponse deactivateUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        user.setActive(false);
        userRepository.save(user);
        return userMapper.toResponse(user);
    }
}
