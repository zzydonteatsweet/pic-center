package com.zzy.piccenter.demos.web.infrastructure.interceptor;

import com.alibaba.fastjson2.JSON;
import com.zzy.piccenter.demos.web.domain.user.User;
import com.zzy.piccenter.demos.web.infrastructure.common.enums.RedisEnum;
import com.zzy.piccenter.demos.web.infrastructure.common.utils.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-01-18 12:12
 **/
@Component
public class RefreshTokenInterceptor implements HandlerInterceptor {
    private StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getHeader("token");
//        String token = request.getHeader("authorization");
//        if (StringUtils.isBlank(token)) {
//            return true;
//        }
//        User user = JSON.parseObject(stringRedisTemplate.opsForValue().get(token), User.class);
//        if (Objects.isNull(user)) {
//            return true;
//        }
//        stringRedisTemplate.expire(token, RedisEnum.REDIS_TTL.getValue(), TimeUnit.SECONDS);
        return true;
    }
}
