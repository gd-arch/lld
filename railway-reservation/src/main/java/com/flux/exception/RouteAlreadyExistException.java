package com.flux.exception;

public class RouteAlreadyExistException extends ResourceNotFoundException{
    public RouteAlreadyExistException(String message){
        super(message);
    }
    public RouteAlreadyExistException(){
        super("Route already exist with given route number");
    }
}
