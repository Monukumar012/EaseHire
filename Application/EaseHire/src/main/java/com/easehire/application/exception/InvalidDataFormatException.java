package com.easehire.application.exception;

public class InvalidDataFormatException extends RuntimeException{
    public InvalidDataFormatException(){}
    public InvalidDataFormatException(String msg){
        super(msg);
    }
}
