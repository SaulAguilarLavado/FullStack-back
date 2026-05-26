package com.ticketflow.FullStack_back.controllers;

import com.ticketflow.FullStack_back.dto.role.RoleRequest;
import com.ticketflow.FullStack_back.dto.role.RoleResponse;
import com.ticketflow.FullStack_back.shared.response.ApiResponse;
import com.ticketflow.FullStack_back.services.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
@Validated
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<RoleResponse>>> getRoles(Pageable pageable) {
        Page<RoleResponse> page = roleService.getRoles(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Roles encontrados", page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponse>> getRole(@PathVariable Integer id) {
        RoleResponse response = roleService.getRoleById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Rol encontrado", response));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponse>> createRole(@Valid @RequestBody RoleRequest request) {
        RoleResponse response = roleService.createRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Rol creado", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponse>> updateRole(@PathVariable Integer id, @Valid @RequestBody RoleRequest request) {
        RoleResponse response = roleService.updateRole(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Rol actualizado", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteRole(@PathVariable Integer id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Rol eliminado", id.toString()));
    }
}
