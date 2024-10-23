package com.example.apartmentmanagement.service.permission.impl;

import com.example.apartmentmanagement.dto.request.AppUserRequest;
import com.example.apartmentmanagement.dto.respone.AuthenticationResponse;
import com.example.apartmentmanagement.entities.permission.UserRole;
import com.example.apartmentmanagement.entities.permission.AppRole;
import com.example.apartmentmanagement.entities.permission.AppUser;
import com.example.apartmentmanagement.repository.permission.RoleRepository;
import com.example.apartmentmanagement.repository.permission.UserRepository;
import com.example.apartmentmanagement.service.permission.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<AppUser> searchAllCustomerByUserCodeOrFullName(String userCode, String fullName, Pageable pageable) {
        return userRepository.searchAllByUserCodeOrFullNameAndRoleId(userCode, fullName, pageable);
    }

    @Override
    public Page<AppUser> searchAllEmployeeByUserCodeOrFullName(String userCode, String fullName, Pageable pageable) {
        return userRepository.searchAllEmployeeByUserCodeOrFullNameAndRoleId(userCode, fullName, pageable);
    }

    @Override
    public AppUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public AppUser findByUserCode(String userCode) {
        AppUser appUser=userRepository.findByUserCode(userCode);
        if(userCode!=null){
            return appUser;
        }
        return null;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<AppUser> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public AppUser findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(AppUser appUser) {
        userRepository.save(appUser);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void disableUser(Long id) {
        userRepository.disableUser(id);
    }

    @Override
    public void enableUser(Long id) {
        userRepository.enableUser(id);
    }

    /**
     * Saves a new {@link AppUser} entity based on the provided {@link AppUserRequest}.
     * Encrypts the password using the configured {@link PasswordEncoder}.
     *
     * @param appUserRequest The {@link AppUserRequest} containing user details to save.
     * @return An {@link AuthenticationResponse} indicating the status of the save operation.
     */
    @Override
    public AuthenticationResponse saveUser(AppUserRequest appUserRequest) {
        String email = appUserRequest.getEmail();
        if (appUserRequest.getPassword() == null || appUserRequest.getPassword().isEmpty()) {
            appUserRequest.setPassword("123");
        }
        String password = passwordEncoder.encode(appUserRequest.getPassword());
        LocalDateTime dateCreate = LocalDateTime.now();
        AppUser appUser = new AppUser(
                appUserRequest.getEmail(),
                password,
                appUserRequest.getUserCode(),
                dateCreate,
                appUserRequest.getFullName(),
                appUserRequest.getGender(),
                appUserRequest.getDateOfBirth(),
                appUserRequest.getPhoneNumber(),
                appUserRequest.getAddress(),
                true,
                true,
                true,
                true
        );

        List<UserRole> userRoles = appUserRequest.getUserRoles();
        if (userRoles != null) {
            for (UserRole userRole : userRoles) {
                userRole.setAppUser(appUser);
            }
            appUser.setUserRoles(userRoles);
        }
        assert userRoles != null;
        if (!userRoles.isEmpty()) {
            appUser.setUserCode(createUserCode(userRoles.getFirst().getAppRole()));
        }
        try {
            userRepository.save(appUser);
        }catch (Exception e) {
            return AuthenticationResponse.builder()
                    .statusCode(400)
                    .message("Cannot add a new User, maybe the user already exists!")
                    .build();
        }
        return AuthenticationResponse.builder()
                .statusCode(200)
                .message("Added successfully!\n" + "Email: " + email + "\n" + "Password: " + appUserRequest.getPassword())
                .build();
    }

    /**
     * Updates an existing {@link AppUser} entity based on the provided user ID and {@link AppUserRequest}.
     * Encrypts the password using the configured {@link PasswordEncoder}.
     *
     * @param userId         The ID of the user to update.
     * @param appUserRequest The {@link AppUserRequest} containing updated user details.
     * @return An {@link AuthenticationResponse} indicating the status of the update operation.
     */
    @Override
    public AuthenticationResponse updateUser(Long userId, AppUserRequest appUserRequest) {
        Optional<AppUser> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return AuthenticationResponse.builder()
                    .statusCode(400)
                    .message("No results found!")
                    .build();
        }
        AppUser appUser = user.get();
        if (appUserRequest.getPassword() != null || !appUserRequest.getPassword().isEmpty()) {
            appUser.setPassword(passwordEncoder.encode(appUserRequest.getPassword()));
        }
        appUser.setUserCode(appUserRequest.getUserCode());
        appUser.setFullName(appUserRequest.getFullName());
        appUser.setGender(appUserRequest.getGender());
        appUser.setDateOfBirth(appUserRequest.getDateOfBirth());
        appUser.setPhoneNumber(appUserRequest.getPhoneNumber());
        appUser.setAddress(appUserRequest.getAddress());
        appUser.setAccountNonExpired(appUserRequest.getAccountNonExpired());
        appUser.setCredentialsNonExpired(appUserRequest.getCredentialsNonExpired());
        appUser.setAccountNonLocked(appUserRequest.getAccountNonLocked());
        appUser.setEnabled(appUserRequest.getEnabled());

        List<UserRole> userRoles = appUserRequest.getUserRoles();
        if (userRoles != null) {
            for (UserRole userRole : userRoles) {
                if (userRole.getId() == null) {
                    userRole.setAppUser(appUser);
                }
            }
            appUser.setUserRoles(userRoles);
        }

        try {
            userRepository.save(appUser);
        }catch (Exception e) {
            return AuthenticationResponse.builder()
                    .statusCode(400)
                    .message("Unable to update employee, system error!")
                    .build();
        }
        return AuthenticationResponse.builder()
                .statusCode(200)
                .message("Update Successful!")
                .build();
    }


    private String createUserCode(AppRole role) {
        List<AppUser> users;
        String userNewCode;
        if (role.getRoleName().equals("ROLE_CUSTOMER")) {
            users = userRepository.findAllByRoleCustomer();
            userNewCode = "CU";
        } else {
            users = userRepository.findAllByRoleEmployee();
            userNewCode = "EM";
        }

        int codeNumber = 1;
        if (!users.isEmpty()) {
            String userCode = users.getLast().getUserCode();
            codeNumber =  Integer.parseInt(userCode.substring(2)) + 1;
        }
        if (codeNumber < 10) {
            userNewCode += "000" + codeNumber;
        } else if (codeNumber < 100) {
            userNewCode += "00" + codeNumber;
        } else if (codeNumber < 1000) {
            userNewCode += "0" + codeNumber;
        } else {
            userNewCode += codeNumber;
        }
        return userNewCode;
    }

}
