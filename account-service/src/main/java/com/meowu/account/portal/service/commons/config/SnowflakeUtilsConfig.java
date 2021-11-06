package com.meowu.account.portal.service.commons.config;

import com.meowu.account.portal.service.commons.security.exception.ConfigurationException;
import com.meowu.account.portal.service.core.snowflake.service.SnowflakeService;
import com.meowu.commons.utils.utils.IPUtils;
import com.meowu.commons.utils.utils.SnowflakeUtils;
import com.meowu.support.portal.client.snowflake.entity.Snowflake;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RefreshScope
@Configuration
public class SnowflakeUtilsConfig{

    @Value("${spring.application.name}")
    private String application;

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private SnowflakeService snowflakeService;

    @RefreshScope
    @Bean
    public SnowflakeUtils snowflakeUtils() throws ConfigurationException{
        // ip
        List<String> ips = IPUtils.findByUsing();

        if(CollectionUtils.isEmpty(ips)){
            throw new ConfigurationException("cannot get host ip address");
        }

        // find snowflake
        Snowflake snowflake = snowflakeService.get(application, ips.get(0), port);

        // create util
        return new SnowflakeUtils(snowflake.getCenterId(), snowflake.getWorkerId());
    }
}
