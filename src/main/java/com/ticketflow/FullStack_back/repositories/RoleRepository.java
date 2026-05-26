package com.ticketflow.FullStack_back.repositories;

import com.ticketflow.FullStack_back.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
