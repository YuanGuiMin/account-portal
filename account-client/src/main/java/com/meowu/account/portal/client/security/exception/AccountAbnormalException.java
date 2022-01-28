package com.meowu.account.portal.client.security.exception;

import com.meowu.commons.utils.security.exception.MeowuRuntimeException;

import java.text.MessageFormat;

public class AccountAbnormalException extends MeowuRuntimeException{

    public AccountAbnormalException(){
    }

    public AccountAbnormalException(String message){
        super(message);
    }

    public AccountAbnormalException(Throwable cause){
        super(cause);
    }

    public AccountAbnormalException(String message, Throwable cause){
        super(message, cause);
    }

    public AccountAbnormalException(String message, Object... args){
        super(MessageFormat.format(message, args));
    }

    public AccountAbnormalException(Throwable cause, String message, Object... args){
        super(MessageFormat.format(message, args), cause);
    }
}
