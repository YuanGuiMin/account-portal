package com.meowu.account.portal.service.core.init;

import com.meowu.account.portal.service.commons.security.stereotype.Initializer;
import com.meowu.account.portal.service.core.key.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@Initializer
public class KeyInitializer implements ApplicationRunner{

    @Autowired
    private KeyService keyService;

    @Override
    public void run(ApplicationArguments args){
        // password rsa key
        keyService.createPasswordRSAKey();
    }
}
