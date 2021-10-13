package com.meowu.account.portal.service.core.key.manager;

import com.meowu.account.portal.service.commons.security.stereotype.Manager;
import com.meowu.account.portal.service.core.key.dao.KeyDao;
import com.meowu.commons.utils.utils.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedis;

@Manager
public class KeyManager{

    @Autowired
    private KeyDao keyDao;

    public String getContentByName(ShardedJedis jedis, String name){
        AssertUtils.notNull(jedis, "redis client must not be null");
        AssertUtils.hasText(name, "key name must not be null");

        return keyDao.getContentByName(jedis, name);
    }
}
