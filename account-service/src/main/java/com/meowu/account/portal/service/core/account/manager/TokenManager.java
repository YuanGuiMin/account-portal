package com.meowu.account.portal.service.core.account.manager;

import ch.qos.logback.core.util.TimeUtil;
import com.meowu.account.portal.client.account.entity.Account;
import com.meowu.account.portal.client.account.entity.User;
import com.meowu.account.portal.client.account.entity.view.AccountVO;
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
        AccountVO view = new AccountVO(token, account, user);
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
        return ShardedJedisHelper.ttl(jedis, cacheName);
    }

    public Long refreshExpireTime(ShardedJedis jedis, String token){
        AssertUtils.notNull(jedis, "redis client must not be null");
        AssertUtils.hasText(token, "token must not be null");

        //缓存名
        String cacheName = AccountConsts.TOKEN_REDIS + token;
        //查询过期时间
        Long expire = ShardedJedisHelper.ttl(jedis, cacheName);

        //更新Token时间
        if(expire != null && expire <= AccountConsts.MINIMUM_UPDATE_EXPIRE){
            ShardedJedisHelper.expire(jedis, cacheName, AccountConsts.TOKEN_REDIS_EXPIRE);
        }

        return expire;
    }
}
