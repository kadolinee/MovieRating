package com.epam.kinorating.exception;

public class DBException extends RuntimeException {
    public DBException(String message, Exception cause) {
        super(message, cause);
    }
}
