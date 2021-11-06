package com.meowu.account.portal.service.core.snowflake.manager.client;

import com.meowu.account.portal.service.commons.security.stereotype.Client;
import com.meowu.commons.utils.security.response.Response;
import com.meowu.support.portal.client.snowflake.entity.Snowflake;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "support-service")
@Client
public interface SnowflakeClient{

    @GetMapping(value = "/api/1.0/snowflake", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Snowflake> get(String application, String ip, Integer port);
}
