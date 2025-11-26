package com.ragir.organizer.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(
            ResourceNotFoundException ex, WebRequest request
    ) {

        return error(ex.getMessage(), request, HttpStatus.NOT_FOUND, "Data Not Found Error");
    }

    @ExceptionHandler(InternalServiceException.class)
    public ResponseEntity<?> HandleInterServiceError(
            ResourceNotFoundException ex, WebRequest request
    ) {
        return error(ex.getMessage(), request, HttpStatus.BAD_REQUEST, "Internal Service Error");
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<?> handleDuplicateException(
            DuplicateException ex, WebRequest request
    ) {
        return error(ex.getMessage(), request, HttpStatus.BAD_REQUEST, "Duplicate Resource Error");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleServerException(ConstraintViolationException ex, WebRequest request) {
        return error(ex.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    public ResponseEntity<?> error(
            String errorMessage,
            WebRequest request,
            HttpStatus status,
            String errorType
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("path", request.getDescription(false));
        response.put("status", status.value());
        response.put("error", errorType);
        response.put("details", errorMessage);

        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        // Field-level errors (e.g. @NotBlank, @Positive, etc.)
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        // Class-level errors (e.g. validate with class)
        ex.getBindingResult().getGlobalErrors().forEach(error ->
                errors.put(error.getObjectName(), error.getDefaultMessage())
        );

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("path", request.getDescription(false));
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Parameter Validation Failed");
        response.put("details", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(error -> {
            String field = error.getPropertyPath().toString();
            String message = error.getMessage();
            errors.put(field, message);
        });

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("path", request.getDescription(false));
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Validation Failed Error");
        response.put("details", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
