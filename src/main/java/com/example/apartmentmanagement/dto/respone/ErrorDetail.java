package com.example.apartmentmanagement.dto.respone;

import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetail {

    private String message;
    private Map<String, String> errors;

    /**
     * Constructs an ErrorDetail object with a main error message.
     *
     * @param message The main error message.
     */
    public ErrorDetail(String message) {
        this.message = message;
        this.errors = new HashMap<>();
    }

    /**
     * Constructs an ErrorDetail object with a main error message and a list of field errors.
     *
     * @param message     The main error message.
     * @param fieldErrors The list of field errors to initialize the errors map.
     */
    public ErrorDetail(String message, List<FieldError> fieldErrors) {
        this.message = message;
        this.errors = new HashMap<>();
        fieldErrors.forEach(error -> this.errors.put(error.getField(), error.getDefaultMessage()));
    }

    /**
     * Retrieves the main error message.
     *
     * @return The main error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Retrieves the map of field errors.
     *
     * @return The map containing field errors where keys are field names and values are error messages.
     */
    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * Adds a new field error to the errors map.
     *
     * @param field   The name of the field where the error occurred.
     * @param message The error message describing the issue.
     */
    public void addError(String field, String message) {
        errors.put(field, message);
    }
}
