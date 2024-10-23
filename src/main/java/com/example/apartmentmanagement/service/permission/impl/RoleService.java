package com.example.apartmentmanagement.service.permission.impl;

import com.example.apartmentmanagement.entities.permission.AppRole;
import com.example.apartmentmanagement.repository.permission.RoleRepository;
import com.example.apartmentmanagement.service.permission.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<AppRole> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Page<AppRole> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public AppRole findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public void save(AppRole appRole) {
        roleRepository.save(appRole);
    }

    @Override
    public void remove(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public AppRole findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
