package com.zzy.piccenter.demos.web.infrastructure.manager;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.zzy.piccenter.demos.web.app.manager.QueryCacheManager;
import com.zzy.piccenter.demos.web.infrastructure.common.utils.StringUtils;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-02-01 11:18
 **/
@Component
@Order(1)
@Slf4j
@Qualifier("caffeineCacheManger")
public class CaffeineCacheManger extends QueryCacheManager {
    private final Cache<String, String> LOCAL_CACHE =
            Caffeine.newBuilder().initialCapacity(1024)
                    .maximumSize(10000L)
                    // 缓存 5 分钟移除
                    .expireAfterWrite(3L, TimeUnit.MINUTES)
                    .build();

    @Override
    public void putInfo(String key, String value) {
        if (StringUtils.isBlank(value)) {
            return;
        }
        LOCAL_CACHE.put(key, value);
    }

    @Override
    public String getInfo(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        String res = LOCAL_CACHE.getIfPresent(key);
        if (StringUtil.isNullOrEmpty(key)) {
            return null;
        }
        return res;
    }
}
