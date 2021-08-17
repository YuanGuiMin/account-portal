package com.meowu.account.portal.client.security.exception;

import com.meowu.commons.utils.security.exception.MeowuRuntimeException;

import java.text.MessageFormat;

public class InvalidAccountStateException extends MeowuRuntimeException{

    public InvalidAccountStateException(){
    }

    public InvalidAccountStateException(String message){
        super(message);
    }

    public InvalidAccountStateException(Throwable cause){
        super(cause);
    }

    public InvalidAccountStateException(String message, Throwable cause){
        super(message, cause);
    }

    public InvalidAccountStateException(String message, Object... args){
        super(MessageFormat.format(message, args));
    }

    public InvalidAccountStateException(Throwable cause, String message, Object... args){
        super(MessageFormat.format(message, args), cause);
    }
}
