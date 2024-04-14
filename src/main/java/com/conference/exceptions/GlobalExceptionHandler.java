package com.conference.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.conference.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> HandleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        Map<String, String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String FeildName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            resp.put(FeildName, message);
        });
        return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> HandleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CONFLICT);
    }
}
