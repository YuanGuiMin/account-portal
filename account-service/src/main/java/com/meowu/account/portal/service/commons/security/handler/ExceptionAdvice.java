package com.meowu.account.portal.service.commons.security.handler;

import com.meowu.commons.utils.security.exception.MeowuException;
import com.meowu.commons.utils.security.exception.MeowuRuntimeException;
import com.meowu.commons.utils.security.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionAdvice{

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(value = Exception.class)
    public Response exception(Exception e){
        logger.error(e.getMessage(), e);

        return new Response().failure(e.getLocalizedMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public Response runtimeException(RuntimeException e){
        logger.error(e.getMessage(), e);

        return new Response().failure(e.getLocalizedMessage());
    }

    @ExceptionHandler(value = MeowuException.class)
    public Response meowuException(MeowuException e){
        logger.error(e.getMessage(), e);

        return new Response().failure(e.getLocalizedMessage());
    }

    @ExceptionHandler(value = MeowuRuntimeException.class)
    public Response meowuRuntimeException(MeowuRuntimeException e){
        logger.error(e.getMessage(), e);

        return new Response().failure(e.getLocalizedMessage());
    }
}
