package com.meowu.account.portal.service.core.snowflake.service;

import com.meowu.account.portal.service.core.snowflake.manager.SnowflakeManager;
import com.meowu.support.portal.client.snowflake.entity.Snowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnowflakeServiceImpl implements SnowflakeService{

    @Autowired
    private SnowflakeManager snowflakeManager;

    @Override
    public Snowflake get(String application, String ip, Integer port){
        return snowflakeManager.get(application, ip, port);
    }
}
