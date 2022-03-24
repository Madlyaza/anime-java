package com.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("A bad request has been send. Check your body & headers.");
    }
}
