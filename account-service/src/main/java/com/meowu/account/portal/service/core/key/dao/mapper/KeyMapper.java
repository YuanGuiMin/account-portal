package com.meowu.account.portal.service.core.key.dao.mapper;

import com.meowu.account.portal.service.core.key.entity.Key;
import com.meowu.commons.mybatis.mysql.criteria.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface KeyMapper{

    void save(Key key);

    void update(Key key);

    Key get(@Param("params") Criteria criteria);

    List<Key> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
