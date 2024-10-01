package com.afulvio.booklify.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
@ResponseStatus
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                "There was an error validating your request",
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                "Book not found",
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UserRegisteredException.class)
    public ResponseEntity<ErrorResponse> handleUserRegisteredException(UserRegisteredException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "User is already registered",
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(UserRegisteringException.class)
    public ResponseEntity<ErrorResponse> handleUserRegisteringException(UserRegisteringException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Error saving user",
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(UserUpdatingException.class)
    public ResponseEntity<ErrorResponse> handleUserUpdatingException(UserUpdatingException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Error updating an user",
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }


}
