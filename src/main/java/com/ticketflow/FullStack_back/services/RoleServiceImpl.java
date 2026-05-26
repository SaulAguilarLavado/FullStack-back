package com.ticketflow.FullStack_back.services;

import com.ticketflow.FullStack_back.dto.role.RoleRequest;
import com.ticketflow.FullStack_back.dto.role.RoleResponse;
import com.ticketflow.FullStack_back.mappers.RoleMapper;
import com.ticketflow.FullStack_back.models.Role;
import com.ticketflow.FullStack_back.repositories.RoleRepository;
import com.ticketflow.FullStack_back.shared.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Page<RoleResponse> getRoles(Pageable pageable) {
        return roleRepository.findAll(pageable).map(roleMapper::toResponse);
    }

    @Override
    public RoleResponse getRoleById(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
        return roleMapper.toResponse(role);
    }

    @Override
    @Transactional
    public RoleResponse createRole(RoleRequest request) {
        roleRepository.findByName(request.getName()).ifPresent(role -> {
            throw new DataIntegrityViolationException("El rol ya existe");
        });
        Role role = roleMapper.toEntity(request);
        Role saved = roleRepository.save(role);
        return roleMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public RoleResponse updateRole(Integer id, RoleRequest request) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        return roleMapper.toResponse(roleRepository.save(role));
    }

    @Override
    @Transactional
    public void deleteRole(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
        roleRepository.delete(role);
    }
}
