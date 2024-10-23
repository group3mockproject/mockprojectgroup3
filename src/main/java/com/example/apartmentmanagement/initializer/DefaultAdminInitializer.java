package com.example.apartmentmanagement.initializer;

import com.example.apartmentmanagement.entities.permission.AppRole;
import com.example.apartmentmanagement.entities.permission.AppUser;
import com.example.apartmentmanagement.entities.permission.UserRole;
import com.example.apartmentmanagement.service.permission.IRoleService;
import com.example.apartmentmanagement.service.permission.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class DefaultAdminInitializer implements CommandLineRunner {

    private final IUserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleService roleService;

    public DefaultAdminInitializer(IUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        // Danh sách các vai trò mặc định
        String[] roleNames = {"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_EMPLOYEE", "ROLE_CUSTOMER"};

        // Tạo các vai trò nếu chúng chưa tồn tại
        for (String roleName : roleNames) {
            AppRole role = roleService.findRoleByRoleName(roleName);
            if (role == null) {
                role = new AppRole();
                role.setRoleName(roleName);
                roleService.save(role);
            }
        }

        // Kiểm tra xem đã có admin trong cơ sở dữ liệu chưa
        if (!userService.existsByEmail("admin@gmail.com")) {
            //Tìm kiếm trong DB có Role tên là ROLE_ADMIN không?
            AppRole adminRole = roleService.findRoleByRoleName("ROLE_ADMIN");
            if (adminRole == null) {
                // Nếu chưa có, tạo mới Role ROLE_ADMIN
                adminRole = new AppRole();
                adminRole.setRoleName("ROLE_ADMIN");
                roleService.save(adminRole);
            }
            // Tạo tài khoản admin mặc định
            AppUser admin = new AppUser();
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setEnabled(true);
            admin.setAccountNonExpired(true);
            admin.setAccountNonLocked(true);
            admin.setCredentialsNonExpired(true);

            List<UserRole> userRoles = new ArrayList<>();
            UserRole userRole = new UserRole();
            AppRole role = roleService.findRoleByRoleName("ROLE_ADMIN");
            userRole.setAppRole(role);
            userRole.setAppUser(admin);
            userRoles.add(userRole);
            admin.setUserRoles(userRoles);
            userService.save(admin);
        }
    }
}
