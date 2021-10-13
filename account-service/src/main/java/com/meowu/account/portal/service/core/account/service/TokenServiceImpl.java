package com.meowu.account.portal.service.core.account.service;

import com.meowu.account.portal.service.core.account.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Transactional(readOnly = true)
@Service
public class TokenServiceImpl implements TokenService{

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Autowired
    private TokenManager tokenManager;

    @Override
    public Long expireTime(String token){
        try(ShardedJedis jedis = shardedJedisPool.getResource()){
            return tokenManager.expireTime(jedis, token);
        }
    }

    @Override
    public Long refreshExpireTime(String token){
        try(ShardedJedis jedis = shardedJedisPool.getResource()){
            return tokenManager.refreshExpireTime(jedis, token);
        }
    }
}
