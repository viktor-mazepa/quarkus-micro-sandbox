package com.mazasoft.service.auth.exceptions;

public class SecretException extends Exception{

    private static final String EXCEPTION_MESSAGE = "Incorrect secret value";

    public SecretException() {
        super(EXCEPTION_MESSAGE);
    }
}
