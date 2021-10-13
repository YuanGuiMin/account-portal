package com.meowu.account.portal.service.core.key.service;

import com.meowu.account.portal.service.core.key.consts.KeyConsts;
import com.meowu.account.portal.service.core.key.manager.KeyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Transactional(readOnly = true)
@Service
public class KeyServiceImpl implements KeyService{

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Autowired
    private KeyManager keyManager;

    @Override
    public String getRSAPublicKey(){
        try(ShardedJedis jedis = shardedJedisPool.getResource()){
            return keyManager.getContentByName(jedis, KeyConsts.RSA_PASSWORD_PUBLIC_KEY);
        }
    }
}
