package com.mazasoft.service.auth.exceptions;

public class UnathorizedServiceException extends Exception {
    private static final String EXCEPTION_MESSAGE = "Unauthorized service exception";

    public UnathorizedServiceException() {
        super(EXCEPTION_MESSAGE);
    }
}
