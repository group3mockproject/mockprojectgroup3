package com.example.apartmentmanagement.entities.permission;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_users", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_USER_UK", columnNames = "email"),
                @UniqueConstraint(name = "APP_USER_CODE_UK", columnNames = "user_code")})
public class AppUser {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password", length = 128)
    private String password;

    @Column(name = "user_code")
    private String userCode;

    private String avatar;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @Column(name = "full_name", length = 50)
    private String fullName;

    private Integer gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "SSN")
    private String ssn;

    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;

    @Column(name = "enabled", length = 1)
    private Boolean enabled;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<UserRole> userRoles = new ArrayList<>();

    public AppUser(String email, String password, String userCode, LocalDateTime dateCreate,
                   String fullName, Integer gender, LocalDate dateOfBirth, String phoneNumber, String address,
                   Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled) {
        this.email = email;
        this.password = password;
        this.userCode = userCode;
        this.dateCreate = dateCreate;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }
}
