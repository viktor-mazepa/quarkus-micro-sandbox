package com.mazasoft.service.auth.exceptions;

public class TokenIncorrectException extends Exception {

    private static final String EXCEPTION_MESSAGE = "Incorrect token value";

    public TokenIncorrectException() {
        super(EXCEPTION_MESSAGE);
    }
}
