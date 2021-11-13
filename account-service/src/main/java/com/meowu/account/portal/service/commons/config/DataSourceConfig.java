package com.meowu.account.portal.service.commons.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RefreshScope
public class DataSourceConfig{

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${druid.datasource.minIdle}")
    private Integer minIdle;

    @Value("${druid.datasource.maxActive}")
    private Integer maxActive;

    @Value("${druid.datasource.maxWait}")
    private Integer maxWait;

    @Value("${druid.datasource.initialSize}")
    private Integer initialSize;

    @Value("${druid.datasource.maxOpenPreparedStatements}")
    private Integer maxOpenPreparedStatements;

    @Value("${druid.datasource.timeBetweenEvictionRunsMillis}")
    private Integer timeBetweenEvictionRunsMillis;

    @Value("${druid.datasource.poolPreparedStatements}")
    private Boolean poolPreparedStatements;

    @Value("${druid.datasource.testOnBorrow}")
    private Boolean testOnBorrow;

    @Value("${druid.datasource.testOnReturn}")
    private Boolean testOnReturn;

    @Value("${druid.datasource.defaultAutoCommit}")
    private Boolean defaultAutoCommit;

    @RefreshScope
    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setDefaultAutoCommit(defaultAutoCommit);

        return dataSource;
    }
}
