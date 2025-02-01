package com.zzy.piccenter.demos.web.infrastructure.interceptor;

import com.alibaba.fastjson2.JSON;
import com.zzy.piccenter.demos.web.domain.common.UserRoleEnum;
import com.zzy.piccenter.demos.web.domain.common.UserStateEnum;
import com.zzy.piccenter.demos.web.domain.user.User;
import com.zzy.piccenter.demos.web.infrastructure.annotation.AuthCheck;
import com.zzy.piccenter.demos.web.infrastructure.common.exception.BusinessException;
import com.zzy.piccenter.demos.web.infrastructure.common.exception.ErrorCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2024-12-22 22:55
 **/
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Around(value = "@annotation(authCheck)", argNames = "joinPoint,authCheck")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.requiredRole();

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        User loginUser = (User)request.getSession().getAttribute(UserStateEnum.USER_LOGIN_STATE.getState());
//        String token = request.getHeader("token");
//        String userObj = stringRedisTemplate.opsForValue().get(token);
//        User loginUser = JSON.parseObject(userObj, User.class);
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);

        if (mustRole == null) {
            return joinPoint.proceed();
        }

        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());

        if (userRoleEnum == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        if (UserRoleEnum.ADMIN.equals(mustRoleEnum) && !UserRoleEnum.ADMIN.equals(userRoleEnum)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return joinPoint.proceed();
    }

}
