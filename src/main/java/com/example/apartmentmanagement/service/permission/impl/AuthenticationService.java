package com.example.apartmentmanagement.service.permission.impl;

import com.example.apartmentmanagement.dto.UserInforUserDetails;
import com.example.apartmentmanagement.dto.request.*;
import com.example.apartmentmanagement.dto.respone.AuthenticationResponse;
import com.example.apartmentmanagement.dto.respone.ErrorDetail;
import com.example.apartmentmanagement.entities.permission.UserRole;
import com.example.apartmentmanagement.entities.permission.AppRole;
import com.example.apartmentmanagement.entities.permission.AppUser;
import com.example.apartmentmanagement.repository.permission.RoleRepository;
import com.example.apartmentmanagement.repository.permission.UserRepository;
import com.example.apartmentmanagement.repository.permission.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;

import java.util.*;

/**
 * Service implementation for managing user authentication and registration.
 * This class provides methods for user registration, authentication, and token management.
 * <p>
 * Author: KhangDV
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    private final AuthenticationManager authenticationManager;
    @Autowired
    private View error;

    public AuthenticationResponse authentication(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = userRepository.findByEmail(request.getEmail());
            List<UserRole> userRoles = userRoleRepository.findAllByAppUser(user);
            UserInforUserDetails userDetails = new UserInforUserDetails(user, userRoles);
            var jwtToken = jwtService.generateToken(userDetails);
            var refreshToken = refreshTokenService.createRefreshTokenWithEmail(request.getEmail());
            return AuthenticationResponse.builder()
                    .statusCode(200)
                    .token(jwtToken)
                    .refreshToken(refreshToken.getToken())
                    .avatar(user.getAvatar())
                    .userId(user.getUserId())
                    .fullName(user.getFullName())
                    .message("Login Successfully!!!")
                    .build();
        } catch (Exception e) {
            return AuthenticationResponse.builder()
                    .message("Email or password is incorrect, please re-enter!")
                    .statusCode(500).build();
        }
    }

    /**
     * Retrieves the information of the authenticated user.
     *
     * @param email The username of the authenticated user.
     * @return An {@link AuthenticationResponse} containing user information.
     */
    public AuthenticationResponse getMyInfo(String email) {
        AppUser user = userRepository.findByEmail(email);
        List<UserRole> userRoles = userRoleRepository.findAllByAppUser(user);

        if (user != null) {
            return AuthenticationResponse.builder()
                    .statusCode(200)
                    .message("Thành công!")
                    .userId(user.getUserId())
                    .email(user.getEmail())
                    .userCode(user.getUserCode())
                    .dateCreate(user.getDateCreate())
                    .avatar(user.getAvatar())
                    .dateOfBirth(user.getDateOfBirth())
                    .phoneNumber(user.getPhoneNumber())
                    .userRoles(userRoles)
                    .fullName(user.getFullName())
                    .gender(user.getGender())
                    .address(user.getAddress())
                    .build();
        } else {
            return AuthenticationResponse.builder()
                    .statusCode(404)
                    .message("User not found!")
                    .build();
        }
    }

    /**
     * Updates the password of the authenticated user.
     *
     * @param updatePasswordRequest The user request containing the updated password details.
     * @return An {@link AuthenticationResponse} indicating the status of the password update operation.
     */
    public AuthenticationResponse updatePassword(Long userId, UpdatePasswordRequest updatePasswordRequest) {
        AppUser user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return AuthenticationResponse.builder()
                    .statusCode(404)
                    .message("User not found!")
                    .build();
        }

        ErrorDetail errors = new ErrorDetail("Validation errors");
        String email = updatePasswordRequest.getEmail();

        if (!user.getEmail().equals(email)) {
            errors.addError("email", "Email address is incorrect!");
            return AuthenticationResponse.builder()
                    .statusCode(400)
                    .message("Email address is incorrect!")
                    .errors(errors)
                    .build();
        }
        if (!passwordEncoder.matches(updatePasswordRequest.getOldPassword(), user.getPassword())) {
            errors.addError("oldPassword", "Email address is incorrect!");
            return AuthenticationResponse.builder()
                    .statusCode(400)
                    .message("Password is incorrect!")
                    .errors(errors)
                    .build();
        }
        if (updatePasswordRequest.getOldPassword().equals(updatePasswordRequest.getNewPassword())) {
            errors.addError("newPassword", "Please do not enter your old password!");
            return AuthenticationResponse.builder()
                    .statusCode(400)
                    .message("Please do not enter your old password!")
                    .errors(errors)
                    .build();
        }
        try {
            user.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
            userRepository.save(user);
        } catch (Exception e) {
            return AuthenticationResponse.builder()
                    .statusCode(401)
                    .message("Password update failed")
                    .build();
        }
        return AuthenticationResponse.builder()
                .statusCode(200)
                .message("Password update successfully!")
                .build();
    }

    public AuthenticationResponse updateAvatarImage(UpdateAvatarRequest updateAvatarRequest) {
        String email = updateAvatarRequest.getEmail();
        AppUser user = userRepository.findByEmail(email);
        if (user == null) {
            return AuthenticationResponse.builder()
                    .statusCode(404)
                    .message("User not found!")
                    .build();
        }
        if (updateAvatarRequest.getAvatar() == null) {
            return AuthenticationResponse.builder()
                    .statusCode(400)
                    .message("Image update failed!").build();
        }
        try {
            Long userId = user.getUserId();
            String avatar = updateAvatarRequest.getAvatar();
            userRepository.updateAvatarImage(avatar, userId);
        } catch (Exception e) {
            return AuthenticationResponse.builder()
                    .statusCode(401)
                    .message("Image update failed")
                    .build();
        }
        user.setAvatar(updateAvatarRequest.getAvatar());

        List<UserRole> userRoles = userRoleRepository.findAllByAppUser(user);

        return AuthenticationResponse.builder()
                .statusCode(200)
                .message("Image updated successfully!")
                .userId(user.getUserId())
                .email(user.getEmail())
                .userCode(user.getUserCode())
                .dateCreate(user.getDateCreate())
                .avatar(user.getAvatar())
                .dateOfBirth(user.getDateOfBirth())
                .phoneNumber(user.getPhoneNumber())
                .userRoles(userRoles)
                .fullName(user.getFullName())
                .gender(user.getGender())
                .address(user.getAddress())
                .build();
    }

    public AuthenticationResponse updateInfo(AppUserRequest appUserRequest) {
        Optional<AppUser> user = userRepository.findById(appUserRequest.getUserId());
        if (user.isEmpty()) {
            return AuthenticationResponse.builder()
                    .statusCode(400)
                    .message("Unable to update user, system error!")
                    .build();
        }
        AppUser appUser = user.get();
        appUser.setFullName(appUserRequest.getFullName());
        appUser.setGender(appUserRequest.getGender());
        appUser.setDateOfBirth(appUserRequest.getDateOfBirth());
        appUser.setPhoneNumber(appUserRequest.getPhoneNumber());
        appUser.setAddress(appUserRequest.getAddress());
        List<UserRole> existingRoles = userRoleRepository.findAllByAppUser(appUser);
        List<UserRole> newRoles = appUserRequest.getUserRoles();
        for (UserRole newRole : newRoles) {
            if (newRole.getId() == null) {
                newRole.setAppUser(appUser);
                existingRoles.add(newRole);
            } else {
                boolean roleExists = existingRoles.stream()
                        .anyMatch(existingRole -> existingRole.getId().equals(newRole.getId()));

                if (!roleExists) {
                    newRole.setAppUser(appUser);
                    existingRoles.add(newRole);
                }
            }
        }
        appUser.setUserRoles(existingRoles);
        try {
            userRepository.save(appUser);
        } catch (Exception e) {
            return AuthenticationResponse.builder()
                    .statusCode(400)
                    .message("Unable to update employee, system error!")
                    .build();
        }
        existingRoles = userRoleRepository.findAllByAppUser(appUser);
        return AuthenticationResponse.builder()
                .statusCode(200)
                .message("Updated successfully!")
                .userId(appUser.getUserId())
                .email(appUser.getEmail())
                .userCode(appUser.getUserCode())
                .dateCreate(appUser.getDateCreate())
                .avatar(appUser.getAvatar())
                .dateOfBirth(appUser.getDateOfBirth())
                .phoneNumber(appUser.getPhoneNumber())
                .userRoles(existingRoles)
                .fullName(appUser.getFullName())
                .gender(appUser.getGender())
                .address(appUser.getAddress())
                .build();
    }

    public boolean checkEmail(CheckEmailRequest request) {
        var user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            return false;
        }
        return user.getPhoneNumber().equals(request.getPhoneNumber());
    }

    public AuthenticationResponse forgotPassword(ForgotPasswordRequest request) {
        var user = userRepository.findByEmail(request.getEmail());
        ErrorDetail errors = new ErrorDetail("Validation errors");
        if (user == null) {
            errors.addError("email", "Email address not found!");
            return AuthenticationResponse.builder()
                    .statusCode(400)
                    .message("Email address not found!")
                    .errors(errors)
                    .build();
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return AuthenticationResponse.builder()
                    .statusCode(401)
                    .message("Updating new password failed!")
                    .build();
        }
        return AuthenticationResponse.builder()
                .statusCode(200)
                .message("New password updated successfully!")
                .build();
    }
}
