package com.amex.exception;

public class InvalidCommandInputException extends RuntimeException{

    public InvalidCommandInputException(String exception){
        super(exception);
    }
}
