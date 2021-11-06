package com.meowu.account.portal.service.commons.security.exception;

import com.meowu.commons.utils.security.exception.MeowuRuntimeException;

import java.text.MessageFormat;

public class FeignClientException extends MeowuRuntimeException{

    public FeignClientException(){
        super();
    }

    public FeignClientException(String message){
        super(message);
    }

    public FeignClientException(Throwable cause){
        super(cause);
    }

    public FeignClientException(String message, Throwable cause){
        super(message, cause);
    }

    public FeignClientException(String message, Object... args){
        super(MessageFormat.format(message, args));
    }

    public FeignClientException(Throwable cause, String message, Object... args){
        super(MessageFormat.format(message, args), cause);
    }
}
