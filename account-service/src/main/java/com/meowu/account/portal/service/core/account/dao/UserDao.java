package com.meowu.account.portal.service.core.account.dao;

import com.meowu.account.portal.client.account.entity.Gender;
import com.meowu.account.portal.client.account.entity.User;
import com.meowu.account.portal.service.core.account.dao.mapper.UserMapper;
import com.meowu.commons.mybatis.mysql.criteria.Criteria;
import com.meowu.commons.mybatis.mysql.criteria.Restrictions;
import com.meowu.commons.utils.utils.AssertUtils;
import com.meowu.commons.utils.utils.IDUtils;
import com.meowu.commons.utils.utils.SnowflakeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class UserDao{

    @Autowired
    private SnowflakeUtils snowflakeUtils;

    @Autowired
    private UserMapper mapper;

    public void save(User user){
        AssertUtils.notNull(user, "user must not be null");
        AssertUtils.hasText(user.getAccountId(), "user account id must not be null");

        //设置参数
        user.setId(snowflakeUtils.getId());
        user.setCreateTime(new Date());

        //默认值
        if(StringUtils.isBlank(user.getNickname())){
            user.setNickname(IDUtils.random());
        }
        if(user.getGender() == null){
            user.setGender(Gender.UNKNOWN);
        }

        mapper.save(user);
    }

    public void update(User user){
        AssertUtils.notNull(user, "user must not be null");
        AssertUtils.hasText(user.getId(), "user id must not be null");

        //设置参数
        user.setUpdateTime(new Date());

        mapper.update(user);
    }

    public User getByAccountId(String accountId){
        AssertUtils.hasText(accountId, "user account id must not be null");

        //查询参数
        Criteria criteria = new Criteria();
        criteria.add(Restrictions.eq("accountId", accountId));

        return mapper.get(criteria);
    }
}
