package com.meowu.account.portal.service.commons.security.exception;

import com.meowu.commons.utils.security.exception.MeowuException;

import java.text.MessageFormat;

public class ConfigurationException extends MeowuException{

    public ConfigurationException(){
    }

    public ConfigurationException(String message){
        super(message);
    }

    public ConfigurationException(Throwable cause){
        super(cause);
    }

    public ConfigurationException(String message, Throwable cause){
        super(message, cause);
    }

    public ConfigurationException(String message, Object... args){
        super(MessageFormat.format(message, args));
    }

    public ConfigurationException(Throwable cause, String message, Object... args){
        super(MessageFormat.format(message, args), cause);
    }
}
