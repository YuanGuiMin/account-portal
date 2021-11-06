package com.meowu.account.portal.service.core.snowflake.service;

import com.meowu.support.portal.client.snowflake.entity.Snowflake;

public interface SnowflakeService{

    Snowflake get(String application, String ip, Integer port);
}
