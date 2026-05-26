package com.ticketflow.FullStack_back.services;

import com.ticketflow.FullStack_back.dto.role.RoleRequest;
import com.ticketflow.FullStack_back.dto.role.RoleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    Page<RoleResponse> getRoles(Pageable pageable);
    RoleResponse getRoleById(Integer id);
    RoleResponse createRole(RoleRequest request);
    RoleResponse updateRole(Integer id, RoleRequest request);
    void deleteRole(Integer id);
}
