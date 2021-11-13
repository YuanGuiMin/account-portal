package com.meowu.account.portal.service.core.account.service;

import com.meowu.account.portal.service.core.account.entity.Account;
import com.meowu.account.portal.service.core.account.entity.User;
import com.meowu.account.portal.client.account.entity.response.AccountVO;
import com.meowu.account.portal.client.security.exception.AccountFrozenException;
import com.meowu.account.portal.client.security.exception.AccountLockedException;
import com.meowu.account.portal.client.security.exception.InvalidTokenException;
import com.meowu.account.portal.service.core.account.manager.AccountManager;
import com.meowu.account.portal.service.core.account.manager.TokenManager;
import com.meowu.account.portal.service.core.account.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Transactional(readOnly = true)
@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Autowired
    private AccountManager accountManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private TokenManager tokenManager;

    @Transactional
    @Override
    public void register(String username, String password){
        try(ShardedJedis jedis = shardedJedisPool.getResource()){
            // create
            Account account = accountManager.save(jedis, username, password);
            // create
            User user = userManager.save(account.getId());
        }
    }

    @Override
    public AccountVO login(String username, String password){
        try(ShardedJedis jedis = shardedJedisPool.getResource()){
            // 查询账户信息
            Account account = accountManager.get(jedis, username, password);

            // 判断用户状态
            switch(account.getState()){
                case FROZEN:
                    throw new AccountFrozenException("account[{0}] has been frozen", username);
                case LOCKED:
                    throw new AccountLockedException("account[{0}] has been locked", username);
                default:
                    break;
            }

            // 查询用户信息
            User user = userManager.getByAccountId(account.getId());

            // 创建token信息
            return tokenManager.generate(jedis, account, user);
        }
    }

    @Override
    public void logout(String token){
        try(ShardedJedis jedis = shardedJedisPool.getResource()){
            tokenManager.delete(jedis, token);
        }
    }

    @Override
    public AccountVO getByToken(String token){
        try(ShardedJedis jedis = shardedJedisPool.getResource()){
            // 查询账户信息
            AccountVO account = tokenManager.getAccount(jedis, token);

            // 验证账户信息
            if(account == null){
                throw new InvalidTokenException("Token[{0}] is invalid");
            }

            return account;
        }
    }
}
