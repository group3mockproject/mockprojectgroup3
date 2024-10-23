package com.example.apartmentmanagement.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements Validator {
    @Email(message = "Invalid email", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be blank!")
    private String newEmail;

    @NotEmpty(message = "New password cannot be blank!")
    @Size(min = 8, max = 50, message = "Password must be from 8 to 50 characters!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&/_])[A-Z][A-Za-z\\d@$!%*/?&]{7,49}$",
            message = "Password must start with an uppercase letter, contain at least one lowercase letter, one number, one special character, and must be between 8 and 50 characters long!")
    private String newPassword;

    @NotBlank(message ="Confirm password cannot be empty!")
    private String confirmPassword;

    @Override
    public boolean supports(Class<?> clazz) {
        return UpdatePasswordRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UpdatePasswordRequest updatePasswordRequest = (UpdatePasswordRequest) target;
        String newPasswordCheck = updatePasswordRequest.getNewPassword();
        String confirmPasswordCheck = updatePasswordRequest.getConfirmPassword();
        if (!confirmPasswordCheck.equals(newPasswordCheck)){
            errors.rejectValue("confirmPassword","", "Passwords do not match!!");
        }
    }

}
