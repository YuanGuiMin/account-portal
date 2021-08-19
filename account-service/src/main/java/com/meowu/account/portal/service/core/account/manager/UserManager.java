package com.meowu.account.portal.service.core.account.manager;

import com.meowu.account.portal.client.account.entity.User;
import com.meowu.account.portal.service.commons.security.stereotype.Manager;
import com.meowu.account.portal.service.core.account.dao.UserDao;
import com.meowu.commons.utils.utils.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Manager
public class UserManager{

    @Autowired
    private UserDao userDao;

    public User save(String accountId){
        AssertUtils.hasText(accountId, "user account id must not be null");

        User user = new User();
        user.setAccountId(accountId);
        userDao.save(user);

        return user;
    }

    public User getByAccountId(String accountId){
        AssertUtils.hasText(accountId, "user account id must not be null");

        return userDao.getByAccountId(accountId);
    }
}
