package com.flux.exception;

public class StationAlreadyExistsException extends ResourceNotFoundException {

    public StationAlreadyExistsException(String message){
        super(message);
    }
    public StationAlreadyExistsException(){
        super("Station already exists with following number");
    }
}
