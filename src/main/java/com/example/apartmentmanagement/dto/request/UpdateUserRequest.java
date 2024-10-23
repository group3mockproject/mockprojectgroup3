package com.example.apartmentmanagement.dto.request;

import com.example.apartmentmanagement.entities.permission.AppRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private Long userId;

    private String email;

    private String password;

    private String userCode;

    private String avatar;

    private String backgroundImage;

    private String fullName;

    private LocalDate dateOfBirth;

    private Integer gender;

    private String phoneNumber;

    private String address;

    private List<AppRole> role;

    private LocalDate dateCreate;
}
