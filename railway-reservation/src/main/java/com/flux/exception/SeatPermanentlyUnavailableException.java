package com.flux.exception;

public class SeatPermanentlyUnavailableException extends RuntimeException{
    public SeatPermanentlyUnavailableException() {
        super("seat unavailable");
    }

    public SeatPermanentlyUnavailableException(String message) {
        super(message);
    }
}
