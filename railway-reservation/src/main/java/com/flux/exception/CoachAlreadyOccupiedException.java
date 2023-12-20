package com.flux.exception;

public class CoachAlreadyOccupiedException extends RuntimeException{
    public CoachAlreadyOccupiedException() {
        super("Coach is already occupied");
    }

    public CoachAlreadyOccupiedException(String message) {
        super(message);
    }
}
