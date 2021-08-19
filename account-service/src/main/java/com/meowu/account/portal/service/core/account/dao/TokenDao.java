package com.meowu.account.portal.service.core.account.dao;

import com.meowu.commons.utils.utils.SnowflakeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TokenDao{

    @Autowired
    private SnowflakeUtils snowflakeUtils;

    public String generate(){
        return Long.toString(snowflakeUtils.nextId(), 62);
    }
}
