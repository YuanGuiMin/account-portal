package com.meowu.account.portal.client.security.exception;

import com.meowu.commons.utils.security.exception.MeowuRuntimeException;

import java.text.MessageFormat;

public class InvalidTokenException extends MeowuRuntimeException{

    public InvalidTokenException(){
    }

    public InvalidTokenException(String message){
        super(message);
    }

    public InvalidTokenException(Throwable cause){
        super(cause);
    }

    public InvalidTokenException(String message, Throwable cause){
        super(message, cause);
    }

    public InvalidTokenException(String message, Object... args){
        super(MessageFormat.format(message, args));
    }

    public InvalidTokenException(Throwable cause, String message, Object... args){
        super(MessageFormat.format(message, args), cause);
    }
}
