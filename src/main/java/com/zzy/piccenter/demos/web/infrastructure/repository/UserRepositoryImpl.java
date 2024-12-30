package com.zzy.piccenter.demos.web.infrastructure.repository;

import com.zzy.piccenter.demos.web.app.repository.UserRepository;
import com.zzy.piccenter.demos.web.domain.user.User;
import com.zzy.piccenter.demos.web.infrastructure.common.exception.BusinessException;
import com.zzy.piccenter.demos.web.infrastructure.common.exception.ErrorCode;
import com.zzy.piccenter.demos.web.infrastructure.converter.UserConverter;
import com.zzy.piccenter.demos.web.infrastructure.handler.GlobalExceptionHandler;
import com.zzy.piccenter.demos.web.infrastructure.mapper.UserMapper;
import com.zzy.piccenter.demos.web.infrastructure.po.UserPO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2024-12-19 00:01
 **/
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Resource
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        UserPO userPO = UserConverter.INSTANCE.toUserPO(user);
        return userMapper.insertSelective(userPO);
    }

    @Override
    public User queryUserByUserAccount(String userAccount) {
        UserPO queryUserPO = new UserPO();
        queryUserPO.setUserAccount(userAccount);
        UserPO existUserPO = userMapper.selectSelective(queryUserPO);
        if (Objects.isNull(existUserPO)) {
            GlobalExceptionHandler.businessExceptionHandler(new BusinessException(ErrorCode.NOT_FOUND_ERROR));
        }
        return UserConverter.INSTANCE.toUser(existUserPO);
    }
}
