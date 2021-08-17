package com.meowu.account.portal.service.core.account.dao.mapper;

import com.meowu.account.portal.client.entity.Account;
import com.meowu.commons.mybatis.mysql.criteria.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountMapper{

    void save(Account account);

    void update(Account account);

    Account get(@Param("params") Criteria criteria);

    List<Account> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
