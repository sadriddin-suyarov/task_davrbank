package com.example.demochat.exeption;

public class ObjectExistsException extends RuntimeException{
    public ObjectExistsException(String message) {
        super(message);
    }
}
