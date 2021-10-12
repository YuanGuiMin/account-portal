package com.meowu.account.portal.service.core.account.consts;

import java.util.concurrent.TimeUnit;

public interface AccountConsts{

    //username lock name
    String USERNAME_REDIS_LOCK = "lock:username:";
    //5 minutes
    Long USERNAME_REDIS_LOCK_EXPIRE = TimeUnit.MINUTES.toSeconds(5);

    //token name
    String TOKEN_REDIS = "token:";
    //2 hours
    Long TOKEN_REDIS_EXPIRE = TimeUnit.HOURS.toSeconds(2);

    //10 minutes
    Long MINIMUM_UPDATE_EXPIRE = TimeUnit.MINUTES.toSeconds(10);
}
