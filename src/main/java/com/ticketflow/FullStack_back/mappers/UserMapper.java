package com.ticketflow.FullStack_back.mappers;

import com.ticketflow.FullStack_back.dto.user.UserRequest;
import com.ticketflow.FullStack_back.dto.user.UserResponse;
import com.ticketflow.FullStack_back.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "pwdHash", source = "password")

    @Mapping(target = "active", constant = "true")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(UserRequest request);

    @Mapping(target = "role", source = "role.name")
    UserResponse toResponse(User entity);
}
