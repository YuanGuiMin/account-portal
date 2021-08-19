package com.meowu.account.portal.service.core.account.manager;

import ch.qos.logback.core.util.TimeUtil;
import com.meowu.account.portal.client.account.entity.Account;
import com.meowu.account.portal.client.account.entity.User;
import com.meowu.account.portal.client.account.entity.view.AccountVO;
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
}
