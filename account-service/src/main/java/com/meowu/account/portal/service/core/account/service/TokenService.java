package com.meowu.account.portal.service.core.account.service;

public interface TokenService{

    Long expireTime(String token);

    Long refreshExpireTime(String token);
}
