package com.meowu.account.portal.service.commons.config;

import com.meowu.commons.utils.utils.SnowflakeUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
public class SnowflakeUtilsConfig{

    @RefreshScope
    @Bean
    public SnowflakeUtils snowflakeUtils(){
        return new SnowflakeUtils(0, 0);
    }
}
