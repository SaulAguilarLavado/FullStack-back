package com.ticketflow.FullStack_back.mappers;

import com.ticketflow.FullStack_back.dto.role.RoleRequest;
import com.ticketflow.FullStack_back.dto.role.RoleResponse;
import com.ticketflow.FullStack_back.models.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toEntity(RoleRequest request);
    RoleResponse toResponse(Role entity);
}
