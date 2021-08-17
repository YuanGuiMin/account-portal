package com.meowu.account.portal.client.security.exception;

import com.meowu.commons.utils.security.exception.MeowuRuntimeException;

import java.text.MessageFormat;

public class UsernameDuplicateException extends MeowuRuntimeException{

    public UsernameDuplicateException(){
    }

    public UsernameDuplicateException(String message){
        super(message);
    }

    public UsernameDuplicateException(Throwable cause){
        super(cause);
    }

    public UsernameDuplicateException(String message, Throwable cause){
        super(message, cause);
    }

    public UsernameDuplicateException(String message, Object... args){
        super(MessageFormat.format(message, args));
    }

    public UsernameDuplicateException(Throwable cause, String message, Object... args){
        super(MessageFormat.format(message, args), cause);
    }
}
