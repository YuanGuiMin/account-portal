package com.meowu.account.portal.service.core.account.consts;

import java.util.concurrent.TimeUnit;

public interface AccountConsts{

    //username lock name
    String USERNAME_REDIS_LOCK = "lock:username:";
    //5 minutes
    long USERNAME_REDIS_LOCK_EXPIRE = TimeUnit.MINUTES.toSeconds(5);

    //token name
    String TOKEN_REDIS = "token:";
    //2 hours
    long TOKEN_REDIS_EXPIRE = TimeUnit.HOURS.toSeconds(2);

    //10 minutes
    long MINIMUM_UPDATE_EXPIRE = TimeUnit.MINUTES.toSeconds(10);

    //token status
    long TOKEN_NOT_EXIST_STATUS    = -2L;
    long TOKEN_PERPETUATION_STATUS = -1L;
}
