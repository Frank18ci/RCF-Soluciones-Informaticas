package com.rcf.servicesservice.exception;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
@Hidden
public class ExceptionHandleController {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFound e) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(error.getStatus()).body(error);
    }
    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<?> badRequestException(BadRequest e) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(error.getStatus()).body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
        List<ErrorResponseValidItem> errors = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorResponseValidItem(error.getField(), error.getDefaultMessage()))
                .toList();
        ErrorResponseValid errorResponse = new ErrorResponseValid(
                HttpStatus.BAD_REQUEST.value(),
                errors,
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> globalExceptionHandler(RuntimeException e) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(error.getStatus()).body(error);
    }
    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<?> serviceUnavailableException(ServiceUnavailableException e) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                e.getMessage(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
