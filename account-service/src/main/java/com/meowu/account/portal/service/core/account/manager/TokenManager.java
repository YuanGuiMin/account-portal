package com.meowu.account.portal.service.core.account.manager;

import com.meowu.account.portal.service.commons.utils.AccountUtils;
import com.meowu.account.portal.service.core.account.entity.Account;
import com.meowu.account.portal.service.core.account.entity.User;
import com.meowu.account.portal.client.account.entity.response.AccountVO;
import com.meowu.account.portal.client.security.exception.InvalidTokenException;
import com.meowu.account.portal.service.commons.security.stereotype.Manager;
import com.meowu.account.portal.service.core.account.consts.AccountConsts;
import com.meowu.account.portal.service.core.account.dao.TokenDao;
import com.meowu.commons.redis.sharded.helper.ShardedJedisHelper;
import com.meowu.commons.utils.utils.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedis;

@Manager
public class TokenManager{

    @Autowired
    private TokenDao tokenDao;

    public AccountVO generate(ShardedJedis jedis, Account account, User user){
        AssertUtils.notNull(jedis, "redis client must not be null");
        AssertUtils.notNull(account, "account must not be null");
        AssertUtils.notNull(user, "user must not be null");

        //创建token
        String token = tokenDao.generate();
        //创建token信息
        AccountVO view = AccountUtils.toView(token, account, user);
        //缓存名
        String cacheName = AccountConsts.TOKEN_REDIS + token;
        //保存信息
        ShardedJedisHelper.saveAndExpire(jedis, cacheName, view, AccountConsts.TOKEN_REDIS_EXPIRE);

        return view;
    }

    public AccountVO getAccount(ShardedJedis jedis, String token){
        AssertUtils.notNull(jedis, "redis client must not be null");
        AssertUtils.hasText(token, "token must not be null");

        //缓存名
        String cacheName = AccountConsts.TOKEN_REDIS + token;

        //查询缓存
        return ShardedJedisHelper.get(jedis, cacheName, AccountVO.class);
    }

    public Long expireTime(ShardedJedis jedis, String token){
        AssertUtils.notNull(jedis, "redis client must not be null");
        AssertUtils.hasText(token, "token must not be null");

        //缓存名
        String cacheName = AccountConsts.TOKEN_REDIS + token;

        //查询过期时间
        Long expire = ShardedJedisHelper.ttl(jedis, cacheName);

        if(AccountConsts.TOKEN_NOT_EXIST_STATUS == expire){
            throw new InvalidTokenException("Token[{0}] is invalid");
        }

        return expire;
    }

    public Long refreshExpireTime(ShardedJedis jedis, String token){
        AssertUtils.notNull(jedis, "redis client must not be null");
        AssertUtils.hasText(token, "token must not be null");

        //缓存名
        String cacheName = AccountConsts.TOKEN_REDIS + token;
        //查询过期时间
        Long expire = ShardedJedisHelper.ttl(jedis, cacheName);

        //更新token时间
        if(AccountConsts.TOKEN_NOT_EXIST_STATUS == expire){
            throw new InvalidTokenException("Token[{0}] is invalid");

        }else if(expire != null && AccountConsts.TOKEN_PERPETUATION_STATUS != expire && AccountConsts.MINIMUM_UPDATE_EXPIRE >= expire){
            ShardedJedisHelper.expire(jedis, cacheName, AccountConsts.TOKEN_REDIS_EXPIRE);
        }

        return expire;
    }

    public void delete(ShardedJedis jedis, String token){
        AssertUtils.notNull(jedis, "redis client must not be null");
        AssertUtils.hasText(token, "token must not be null");

        //缓存名
        String cacheName = AccountConsts.TOKEN_REDIS + token;
        //删除token信息
        ShardedJedisHelper.delete(jedis, cacheName);
    }
}
