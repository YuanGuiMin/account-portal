package com.meowu.account.portal.service.core.account.manager;


import com.meowu.account.portal.client.entity.Account;
import com.meowu.account.portal.client.entity.AccountState;
import com.meowu.account.portal.client.security.exception.PasswordException;
import com.meowu.account.portal.client.security.exception.UsernameDuplicateException;
import com.meowu.account.portal.service.commons.security.stereotype.Manager;
import com.meowu.account.portal.service.commons.utils.PasswordUtils;
import com.meowu.account.portal.service.core.account.consts.AccountConsts;
import com.meowu.account.portal.service.core.account.dao.AccountDao;
import com.meowu.commons.utils.utils.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Manager
public class AccountManager{

    @Autowired
    private AccountDao accountDao;

    public Account save(ShardedJedis jedis, String username, String password){
        AssertUtils.notNull(jedis, "redis client must not be null");
        AssertUtils.hasText(username, "username must not be null");
        AssertUtils.hasText(password, "password must not be null");

        //lock name
        String lock = AccountConsts.USERNAME_REDIS_LOCK + username;

        try{
            if(ShardedJedisHelper.setIfNotExist(jedis, lock, 1, AccountConsts.USERNAME_REDIS_LOCK_EXPIRE)){
                if(!accountDao.existByUsername(username)){
                    //rsa key
                    String privateKey = ShardedJedisHelper.get(jedis, KeyConsts.RSA_PASSWORD_PRIVATE_KEY, String.class);

                    //match the password rule
                    if(PasswordUtils.brokenRule(password, privateKey)){
                        throw new PasswordException("password has broken the rule");
                    }

                    //create account
                    Account account = new Account();
                    account.setUsername(username);
                    account.setPassword(password);
                    account.setState(AccountState.NORMAL);
                    accountDao.save(account);

                    return account;
                }
            }

            //throw exception
            throw new UsernameDuplicateException("username[{0}] has been used", username);
        }finally{
            //delete redis lock
            ShardedJedisHelper.delete(jedis, lock);
        }
    }
}