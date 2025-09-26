package com.rcf.servicesservice.exception;

public class BadRequest extends RuntimeException {
    public BadRequest(String message) {
        super(message);
    }
}
