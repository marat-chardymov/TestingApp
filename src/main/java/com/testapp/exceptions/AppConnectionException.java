package com.testapp.exceptions;

public class AppConnectionException extends AppException {
    public AppConnectionException(String message, Exception cause) {
        super(message, cause);
    }
}
