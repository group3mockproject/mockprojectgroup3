package com.example.apartmentmanagement.repository.permission;

import com.example.apartmentmanagement.entities.permission.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String roleName);
}
