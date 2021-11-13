package com.meowu.account.portal.service.core.key.consts;

import java.util.concurrent.TimeUnit;

public interface KeyConsts{

    String RSA_PASSWORD_PUBLIC_KEY  = "key:password:rsa:public";
    String RSA_PASSWORD_PRIVATE_KEY = "key:password:rsa:private";

    // RSA key lock name
    String RSA_REDIS_PASSWORD_LOCK = "lock:password:rsa:";
    //5 minutes
    long RSA_REDIS_PASSWORD_LOCK_EXPIRE = TimeUnit.MINUTES.toSeconds(5);
}
