package com.jojo.authentification_blood_share.exceptrions;

import jakarta.annotation.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends RuntimeException{
   private String message;
    public EmailAlreadyExistsException(String message)
    {
        super(message);
    }
}
