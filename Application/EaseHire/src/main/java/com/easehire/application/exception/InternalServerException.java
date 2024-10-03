package com.easehire.application.exception;

public class InternalServerException extends RuntimeException{
    public InternalServerException(){}
    public InternalServerException(String msg){
        super(msg);
    }
}
