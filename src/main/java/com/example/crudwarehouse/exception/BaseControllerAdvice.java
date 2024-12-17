package com.example.crudwarehouse.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@ControllerAdvice
public class BaseControllerAdvice {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return response(HttpStatus.BAD_REQUEST, ex, errorMessages);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ProductWithThisVendorCodeAlreadyExistsException.class, ProductNotFoundException.class})
    public Object handleMultipleExceptions(Exception ex) {
        return response(HttpStatus.FORBIDDEN, ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SearchOperationNotFoundException.class)
    public Object handleSearchOperationNotFoundException(SearchOperationNotFoundException ex) {
        return response(HttpStatus.FORBIDDEN, ex);
    }

    private ResponseEntity<ErrorDetails> response(HttpStatus status, Exception ex) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .time(LocalDateTime.now().format(dateTimeFormatter))
                .message(ex.getMessage())
                .exceptionName(ex.getClass().getSimpleName())
                .build();
        return ResponseEntity.status(status).body(errorDetails);
    }

    private ResponseEntity<ErrorDetails> response(HttpStatus status, Exception ex, String message) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .time(LocalDateTime.now().format(dateTimeFormatter))
                .message(message)
                .exceptionName(ex.getClass().getSimpleName())
                .build();
        return ResponseEntity.status(status).body(errorDetails);
    }
}
