package com.sanix.Spring_Microservices.exceptions;

public class TodoNotFoundException extends RuntimeException{
    public TodoNotFoundException(String msg) {
        super(msg);
    }
}
