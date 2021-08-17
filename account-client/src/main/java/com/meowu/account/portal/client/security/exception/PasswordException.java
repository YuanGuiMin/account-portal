package com.meowu.account.portal.client.security.exception;

import com.meowu.commons.utils.security.exception.MeowuRuntimeException;

import java.text.MessageFormat;

public class PasswordException extends MeowuRuntimeException{

    public PasswordException(){
    }

    public PasswordException(String message){
        super(message);
    }

    public PasswordException(Throwable cause){
        super(cause);
    }

    public PasswordException(String message, Throwable cause){
        super(message, cause);
    }

    public PasswordException(String message, Object... args){
        super(MessageFormat.format(message, args));
    }

    public PasswordException(Throwable cause, String message, Object... args){
        super(MessageFormat.format(message, args), cause);
    }
}
