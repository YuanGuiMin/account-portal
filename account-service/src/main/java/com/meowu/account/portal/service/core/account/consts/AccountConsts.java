package com.meowu.account.portal.service.core.account.consts;

import java.util.concurrent.TimeUnit;

public interface AccountConsts{

    // username lock name
    String USERNAME_REDIS_LOCK = "lock:username:";
    // 5 minutes
    long USERNAME_REDIS_LOCK_EXPIRE = TimeUnit.MINUTES.toSeconds(5);
}
