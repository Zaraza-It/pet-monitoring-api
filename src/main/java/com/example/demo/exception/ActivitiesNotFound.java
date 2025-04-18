package com.example.demo.exception;

public class ActivitiesNotFound extends RuntimeException {
    public ActivitiesNotFound(String message) {
        super(message);
    }
}
