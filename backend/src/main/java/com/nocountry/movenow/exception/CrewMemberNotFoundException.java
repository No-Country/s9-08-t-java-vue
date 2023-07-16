package com.nocountry.movenow.exception;

public class CrewMemberNotFoundException extends RuntimeException {

    public CrewMemberNotFoundException(String message) {
        super(message);
    }

    public CrewMemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}