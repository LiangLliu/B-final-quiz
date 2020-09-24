package com.example.demo.exception;

public class BaseBadRequestException extends RuntimeException {
    public BaseBadRequestException(String message) {
        super(message);
    }
}
