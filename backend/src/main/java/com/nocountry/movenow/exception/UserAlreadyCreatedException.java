package com.nocountry.movenow.exception;

public class UserAlreadyCreatedException extends RuntimeException {

    public UserAlreadyCreatedException(String message) {
        super(message);
    }

    public UserAlreadyCreatedException(String message, Throwable cause) {
        super(message, cause);
    }


}