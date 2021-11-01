package com.example.demochat.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjectExistsException.class)
    public ResponseEntity<?> handleObjectExistsException (ObjectExistsException exception){
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.CONFLICT.value(), exception.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.CONFLICT);
    }
    }
