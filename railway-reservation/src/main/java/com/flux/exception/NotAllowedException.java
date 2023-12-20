package com.flux.exception;

public class NotAllowedException extends RuntimeException {
    public NotAllowedException() {
        super("action not allowed");
    }

    public NotAllowedException(String message) {
        super(message);
    }
}
