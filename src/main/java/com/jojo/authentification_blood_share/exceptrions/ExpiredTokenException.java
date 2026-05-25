package com.jojo.authentification_blood_share.exceptrions;

public class ExpiredTokenException extends RuntimeException{
    private String message;
    public ExpiredTokenException (String message){
        super(message);
    }}
