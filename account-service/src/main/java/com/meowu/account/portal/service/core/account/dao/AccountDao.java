package com.meowu.account.portal.service.core.account.dao;

import com.meowu.account.portal.client.entity.Account;
import com.meowu.account.portal.service.core.account.dao.mapper.AccountMapper;
import com.meowu.commons.mybatis.mysql.criteria.Criteria;
import com.meowu.commons.mybatis.mysql.criteria.Restrictions;
import com.meowu.commons.utils.utils.AssertUtils;
import com.meowu.commons.utils.utils.SnowflakeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class AccountDao{

    @Autowired
    private SnowflakeUtils snowflakeUtils;

    @Autowired
    private AccountMapper mapper;

    public void save(Account account){
        AssertUtils.notNull(account, "account must not be null");
        AssertUtils.hasText(account.getUsername(), "account username must not be null");
        AssertUtils.hasText(account.getPassword(), "account password must not be null");
        AssertUtils.notNull(account.getState(), "account state must not be null");

        //设置参数
        account.setId(snowflakeUtils.getId());
        account.setDeleted(false);
        account.setCreateTime(new Date());

        mapper.save(account);
    }

    public void update(Account account){
        AssertUtils.notNull(account, "account must not be null");
        AssertUtils.hasText(account.getId(), "account id must not be null");

        //设置参数
        account.setUpdateTime(new Date());

        mapper.update(account);
    }

    public boolean existByUsername(String username){
        AssertUtils.hasText(username, "account username must not be null");

        //查询参数
        Criteria criteria = new Criteria();
        criteria.add(Restrictions.eq("username", username));

        Long total = mapper.count(criteria);

        return (total != null && total > 0);
    }

    public Account getById(String id){
        AssertUtils.hasText(id, "account id must not be null");

        //查询参数
        Criteria criteria = new Criteria();
        criteria.add(Restrictions.eq("id", id));

        return mapper.get(criteria);
    }

    public Account getByUsername(String username){
        AssertUtils.hasText(username, "account username must not be null");

        //查询参数
        Criteria criteria = new Criteria();
        criteria.add(Restrictions.eq("username", username));

        return mapper.get(criteria);
    }

}
