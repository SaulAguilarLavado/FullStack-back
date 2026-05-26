package com.ticketflow.FullStack_back.services;

import com.ticketflow.FullStack_back.dto.user.PasswordChangeRequest;
import com.ticketflow.FullStack_back.dto.user.UserRequest;
import com.ticketflow.FullStack_back.dto.user.UserResponse;
import com.ticketflow.FullStack_back.dto.user.UserUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserResponse> getUsers(Pageable pageable);
    UserResponse getUserById(String id);
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(String id, UserUpdateRequest request);
    UserResponse changePassword(String id, PasswordChangeRequest request);
    UserResponse deactivateUser(String id);
}
