package com.nocountry.movenow.exception;

public class MovingNotFoundException extends RuntimeException {

    public MovingNotFoundException(String message) {
        super(message);
    }

    public MovingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}