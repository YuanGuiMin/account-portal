package com.meowu.account.portal.service.commons.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RefreshScope
public class DataSourceConfig{

    @Value("${druid.datasource.url}")
    private String url;

    @Value("${druid.datasource.username}")
    private String username;

    @Value("${druid.datasource.password}")
    private String password;

    @Value("${druid.datasource.driverClassName}")
    private String driverClassName;

    @Value("${druid.datasource.minIdle}")
    private Integer minIdle;

    @Value("${druid.datasource.maxActive}")
    private Integer maxActive;

    @Value("${druid.datasource.maxWait}")
    private Integer maxWait;

    @Value("${druid.datasource.testOnBorrow}")
    private Boolean testOnBorrow;

    @Value("${druid.datasource.testOnReturn}")
    private Boolean testOnReturn;

    @RefreshScope
    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);

        return dataSource;
    }
}
