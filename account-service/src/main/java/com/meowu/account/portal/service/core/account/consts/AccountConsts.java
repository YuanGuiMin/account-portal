package com.meowu.account.portal.service.core.account.consts;

public interface AccountConsts{

    String USERNAME_REDIS_LOCK = "lock:username:";

    //300 seconds
    Integer USERNAME_REDIS_LOCK_EXPIRE = 300;
}
