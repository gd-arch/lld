package com.flux.exception;

public class SeatTemporaryUnavailbleException extends RuntimeException{
    public SeatTemporaryUnavailbleException() {
        super("seat temporarily unavailable");
    }

    public SeatTemporaryUnavailbleException(String message) {
        super(message);
    }
}
