package com.example.apartmentmanagement.dto.request;

import com.example.apartmentmanagement.entities.permission.AppRole;
import com.example.apartmentmanagement.entities.permission.UserRole;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRequest implements Validator {
    private Long userId;

    @Email(message = "Email cannot be blank!")
    @NotBlank(message = "Email cannot be blank!")
    private String email;

    private String password;

    private List<UserRole> userRoles;

    private String userCode;

    private String avatar;

    @NotBlank(message = "Name cannot be blank!")
    @Size(max = 50, message = "Full name must not exceed 50 characters!")
    @Pattern(regexp = "^[A-Za-zÀ-ỹà-ỹĂăÂâÊêÔôƠơƯưĐđ\\s]+$", message = "User's full name must begin with a CAPITAL letter, must not contain numeric characters, and must not contain special characters!")
    private String fullName;

    @NotNull(message = "Date of birth cannot be left blank!")
    private LocalDate dateOfBirth;

    @NotNull(message = "Giới tính không được bỏ trống!")
    private Integer gender;

    @NotBlank(message = "Phone number cannot be left blank!")
    @Pattern(regexp = "^(?:\\+84|0)\\d{9}$", message = "The phone number must start with +84 or 0 and end with 9 digits!")
    private String phoneNumber;

    @NotBlank(message = "Address cannot be left blank!")
    @Length(min = 0, max = 255, message = "The address cannot exceed a maximum of 255 characters!")
    private String address;

    private LocalDateTime dateCreate;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    private Boolean enabled;

    @Override
    public boolean supports(Class<?> clazz) {
        return AppUserRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AppUserRequest appUserRequest = (AppUserRequest) target;
        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfBirthCheck = appUserRequest.getDateOfBirth();
        int acceptYear = Period.between(dateOfBirthCheck, currentDate).getYears();
        if (acceptYear < 18) {
            errors.rejectValue("dateOfBirth","", "Date of birth must be 18 years greater than current date!!");
        }
    }
}
