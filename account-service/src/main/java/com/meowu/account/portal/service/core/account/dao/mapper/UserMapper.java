package com.meowu.account.portal.service.core.account.dao.mapper;

import com.meowu.account.portal.service.core.account.entity.User;
import com.meowu.commons.mybatis.mysql.criteria.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper{

    void save(User user);

    void update(User user);

    User get(@Param("params") Criteria criteria);

    List<User> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
