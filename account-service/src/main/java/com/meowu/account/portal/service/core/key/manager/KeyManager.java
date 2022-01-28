package com.meowu.account.portal.service.core.key.manager;

import com.meowu.account.portal.service.commons.security.stereotype.Manager;
import com.meowu.account.portal.service.core.key.consts.KeyConsts;
import com.meowu.account.portal.service.core.key.dao.KeyDao;
import com.meowu.account.portal.service.core.key.entity.Key;
import com.meowu.commons.redis.sharded.helper.ShardedJedisHelper;
import com.meowu.commons.utils.utils.AssertUtils;
import com.meowu.commons.utils.utils.Base64Utils;
import com.meowu.commons.utils.utils.RSAUtils;
import com.meowu.commons.utils.utils.SpellUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedis;

import java.util.Map;

@Manager
public class KeyManager{

    @Autowired
    private KeyDao keyDao;

    public String getContentByName(ShardedJedis jedis, String name){
        AssertUtils.notNull(jedis, "redis client must not be null");
        AssertUtils.hasText(name, "key name must not be null");

        return keyDao.getContentByName(jedis, name);
    }

    public void createPasswordRSAKey(ShardedJedis jedis){
        AssertUtils.notNull(jedis, "redis client must not be null");

        // lock name
        String lock = KeyConsts.RSA_REDIS_PASSWORD_LOCK + "create";

        try{
            if(ShardedJedisHelper.setIfNotExist(jedis, lock, "1", KeyConsts.RSA_REDIS_PASSWORD_LOCK_EXPIRE)){
                if(!keyDao.existByName(KeyConsts.RSA_PASSWORD_PUBLIC_KEY) && !keyDao.existByName(KeyConsts.RSA_PASSWORD_PRIVATE_KEY)){
                    Map<String, java.security.Key> pairs = RSAUtils.keyPairBy2048();

                    // create keys
                    java.security.Key publicKey  = pairs.get(RSAUtils.PUBLIC_KEY);
                    java.security.Key privateKey = pairs.get(RSAUtils.PRIVATE_KEY);

                    // key contents
                    String publicKeyContent = SpellUtils.toString(Base64Utils.encode(publicKey.getEncoded()));
                    String privateKeyContent = SpellUtils.toString(Base64Utils.encode(privateKey.getEncoded()));

                    // save keys
                    Key rsaPublicKey = new Key();
                    rsaPublicKey.setName(KeyConsts.RSA_PASSWORD_PUBLIC_KEY);
                    rsaPublicKey.setContent(publicKeyContent);
                    keyDao.save(rsaPublicKey);

                    Key rsaPrivateKey = new Key();
                    rsaPrivateKey.setName(KeyConsts.RSA_PASSWORD_PRIVATE_KEY);
                    rsaPrivateKey.setContent(privateKeyContent);
                    keyDao.save(rsaPrivateKey);
                }

                ShardedJedisHelper.delete(jedis, lock);
            }
        }catch(Exception e){
            ShardedJedisHelper.delete(jedis, lock);

            // throw exception anyway
            throw e;
        }
    }
}
