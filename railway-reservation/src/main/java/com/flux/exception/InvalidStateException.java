package com.flux.exception;

public class InvalidStateException extends RuntimeException {
    public InvalidStateException() {
        super("Invalid State");
    }

    public InvalidStateException(String message) {
        super(message);
    }
}
