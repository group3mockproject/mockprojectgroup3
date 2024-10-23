package com.example.apartmentmanagement.dto.respone;

import com.example.apartmentmanagement.entities.permission.AppRole;
import com.example.apartmentmanagement.entities.permission.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Response DTO for authentication-related operations.
 * Contains various fields to represent authentication status, user details,
 * and related information in a structured format.
 * <p>
 * Author: KhangDV
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private int statusCode;

    private ErrorDetail errors;

    private String message;

    private String token;

    private String refreshToken;

    private Long userId;

    private String email;

    private LocalDateTime dateCreate;

    private String fullName;

    private String userCode;

    private String avatar;

    private Integer gender;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    private String address;

    private List<UserRole> userRoles;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    private Boolean enabled;

}