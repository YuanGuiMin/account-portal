package com.meowu.account.portal.client.security.exception;

import com.meowu.commons.utils.security.exception.MeowuRuntimeException;

import java.text.MessageFormat;

public class AccountNotFoundException extends MeowuRuntimeException{

    public AccountNotFoundException(){
    }

    public AccountNotFoundException(String message){
        super(message);
    }

    public AccountNotFoundException(Throwable cause){
        super(cause);
    }

    public AccountNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public AccountNotFoundException(String message, Object... args){
        super(MessageFormat.format(message, args));
    }

    public AccountNotFoundException(Throwable cause, String message, Object... args){
        super(MessageFormat.format(message, args), cause);
    }
}
