package com.meowu.account.portal.service.core.snowflake.manager;

import com.meowu.account.portal.service.commons.security.exception.FeignClientException;
import com.meowu.account.portal.service.commons.security.response.ResponseCode;
import com.meowu.account.portal.service.commons.security.stereotype.Manager;
import com.meowu.account.portal.service.core.snowflake.manager.client.SnowflakeClient;
import com.meowu.commons.utils.security.response.Response;
import com.meowu.commons.utils.utils.AssertUtils;
import com.meowu.support.portal.client.snowflake.entity.Snowflake;
import org.springframework.beans.factory.annotation.Autowired;

@Manager
public class SnowflakeManager{

    @Autowired
    private SnowflakeClient snowflakeClient;

    public Snowflake get(String application, String ip, Integer port){
        AssertUtils.hasText(application, "application name must not be null");
        AssertUtils.hasText(ip, "application ip must not be null");
        AssertUtils.notNull(port, "application port must not be null");

        Response<Snowflake> response = snowflakeClient.get(application, ip, port);

        // get data
        if(ResponseCode.SUCCESS == response.getCode() && response.getSuccess()){
            return response.getData();
        }

        // exception
        throw new FeignClientException("feign client error: get snowflake failed");
    }
}
