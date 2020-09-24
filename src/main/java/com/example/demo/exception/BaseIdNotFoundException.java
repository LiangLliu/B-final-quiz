package com.example.demo.exception;

public class BaseIdNotFoundException extends RuntimeException {
    public BaseIdNotFoundException(String message) {
        super(message);
    }

    public BaseIdNotFoundException() {
    }
}
