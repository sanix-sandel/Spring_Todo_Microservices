package com.sanix.Spring_Microservices.bean;

public class WelcomeBean {

    private String message;

    public WelcomeBean(String message){
        super();
        this.message=message;
    }

    public String getMessage(){
        return message;
    }
}
