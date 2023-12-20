package com.flux.exception;

public class TrainAlreadyExistsException extends RuntimeException {
    public TrainAlreadyExistsException(String message) {
        super(message);
    }
}
