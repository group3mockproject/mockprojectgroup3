package com.example.apartmentmanagement.repository.permission;

import com.example.apartmentmanagement.entities.permission.UserRole;
import com.example.apartmentmanagement.entities.permission.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findAllByAppUser(AppUser appUser);
}
