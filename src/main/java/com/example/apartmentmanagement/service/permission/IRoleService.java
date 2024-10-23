package com.example.apartmentmanagement.service.permission;

import com.example.apartmentmanagement.entities.permission.AppRole;
import com.example.apartmentmanagement.service.IGeneralService;

public interface IRoleService extends IGeneralService<AppRole> {
    AppRole findRoleByRoleName(String roleName);
}
