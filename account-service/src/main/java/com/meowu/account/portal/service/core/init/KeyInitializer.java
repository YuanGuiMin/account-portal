package com.meowu.account.portal.service.core.init;

import com.meowu.account.portal.service.commons.security.stereotype.Initializer;
import com.meowu.account.portal.service.core.key.service.KeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@Initializer
public class KeyInitializer implements ApplicationRunner{

    private static Logger logger = LoggerFactory.getLogger(KeyInitializer.class);

    @Autowired
    private KeyService keyService;

    @Override
    public void run(ApplicationArguments args){
        // password rsa key
        keyService.createPasswordRSAKey();

        logger.info("password rsa key initialize successful");
    }
}
