package com.meowu.account.portal.service.core.key.dao;

import com.meowu.account.portal.service.core.key.dao.mapper.KeyMapper;
import com.meowu.account.portal.service.core.key.entity.Key;
import com.meowu.commons.mybatis.mysql.criteria.Criteria;
import com.meowu.commons.mybatis.mysql.criteria.Restrictions;
import com.meowu.commons.redis.sharded.helper.ShardedJedisHelper;
import com.meowu.commons.utils.utils.SnowflakeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import redis.clients.jedis.ShardedJedis;

import java.util.Date;

@Repository
public class KeyDao{

    @Autowired
    private SnowflakeUtils snowflakeUtils;

    @Autowired
    private KeyMapper mapper;

    public void save(Key key){
        Assert.notNull(key, "key must not be null");
        Assert.hasText(key.getContent(), "key content must not be null");
        Assert.hasText(key.getName(), "key name must not be null");

        key.setId(snowflakeUtils.getId());
        key.setCreateTime(new Date());

        mapper.save(key);
    }

    public void update(Key key){
        Assert.notNull(key, "key must not be null");
        Assert.hasText(key.getId(), "key id must not be null");

        key.setUpdateTime(new Date());

        mapper.update(key);
    }

    public boolean existByName(String name){
        Assert.hasText(name, "key name must not be null");

        Criteria criteria = new Criteria();
        criteria.add(Restrictions.eq("name", name));

        Long total = mapper.count(criteria);

        return (total != null && total > 0);
    }

    public String getContentByName(String name){
        Assert.hasText(name, "key name must not be null");

        Criteria criteria = new Criteria();
        criteria.add(Restrictions.eq("name", name));

        Key key = mapper.get(criteria);

        return key == null ? null : key.getContent();
    }

    public String getContentByName(ShardedJedis jedis, String name){
        Assert.notNull(name, "key name must not be null");

        if(jedis == null){
            return getContentByName(name);
        }

        String key = ShardedJedisHelper.get(jedis, name, String.class);
        if(StringUtils.isBlank(key)){
            key = getContentByName(name);

            if(StringUtils.isNotBlank(key)){
                ShardedJedisHelper.save(jedis, name, key);
            }
        }

        return key;
    }
}
