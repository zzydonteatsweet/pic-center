package com.zzy.piccenter.demos.web.infrastructure.manager;

import cn.hutool.core.util.RandomUtil;
import com.zzy.piccenter.demos.web.app.manager.QueryCacheManager;
import com.zzy.piccenter.demos.web.infrastructure.common.enums.NullEnum;
import com.zzy.piccenter.demos.web.infrastructure.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-02-01 11:18
 **/
@Component
@Order(2)
@Slf4j
@Qualifier("redisCacheManager")
public class RedisCacheManager extends QueryCacheManager {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private CaffeineCacheManger caffeineCacheManger;

    /**
     * 1.过期时间随机
     *
     * @param key
     * @param value
     */
    @Override
    public void putInfo(String key, String value) {
        ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
        operations.set(key, value);
        int expireTime = RandomUtil.randomInt(MIN_TTL_MINUTE, MAX_TTL_MINUTE);
        stringRedisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
        caffeineCacheManger.putInfo(key, value);
    }

    /**
     *
     * 2.查询到空值设置空对象(应该在数据库中做)
     * @param key
     * @return
     */
    @Override
    public String getInfo(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        String res = stringRedisTemplate.opsForValue().get(key);
        if (NullEnum.NULL.getValue().equals(res)) {
            return null;
        }
        caffeineCacheManger.putInfo(key, res);
        return res;
    }
}
