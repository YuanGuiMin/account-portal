package com.meowu.account.portal.service.commons.config;

import com.meowu.account.portal.service.commons.security.exception.ConfigurationException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RefreshScope
public class ShardedJedisConfig{

    @Value("${redis.sharded.nodes}")
    private List<String> nodes;

    @Value("${redis.sharded.maxTotal}")
    private Integer maxTotal;

    @Value("${redis.sharded.maxIdle}")
    private Integer maxIdle;

    @Value("${redis.sharded.minIdle}")
    private Integer minIdle;

    @Value("${redis.sharded.maxWaitMillis}")
    private Integer maxWaitMillis;

    @Value("${redis.sharded.testOnBorrow}")
    private Boolean testOnBorrow;

    @Value("${redis.sharded.testOnReturn}")
    private Boolean testOnReturn;

    @RefreshScope
    @Bean
    public ShardedJedisPool shardedJedisPool() throws ConfigurationException{
        if(CollectionUtils.isEmpty(nodes)){
            throw new ConfigurationException("redis nodes must not be null");
        }

        //nodes info
        List<JedisShardInfo> shardInfoList = nodes.stream()
                                                  .map(node -> new JedisShardInfo(node))
                                                  .collect(Collectors.toList());

        //create the config
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);

        //create and return the pool
        return new ShardedJedisPool(config, shardInfoList);
    }
}
