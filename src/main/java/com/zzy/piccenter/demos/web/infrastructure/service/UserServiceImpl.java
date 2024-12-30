package com.zzy.piccenter.demos.web.infrastructure.service;

import com.zzy.piccenter.demos.web.app.assembler.UserAssembler;
import com.zzy.piccenter.demos.web.app.dto.UserInfoDTO;
import com.zzy.piccenter.demos.web.app.repository.UserRepository;
import com.zzy.piccenter.demos.web.app.request.UserLoginDTO;
import com.zzy.piccenter.demos.web.app.request.UserRegisterDTO;
import com.zzy.piccenter.demos.web.app.service.UserService;
import com.zzy.piccenter.demos.web.domain.common.UserStateEnum;
import com.zzy.piccenter.demos.web.domain.user.User;
import com.zzy.piccenter.demos.web.infrastructure.common.exception.BusinessException;
import com.zzy.piccenter.demos.web.infrastructure.common.exception.ErrorCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2024-12-18 23:58
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    /**
     * 1. 检查用户账号是否已经在数据库中存在
     * 2. 插入数据
     *
     * @param userRegisterDTO
     * @return
     */
    @Override
    public Integer userRegister(UserRegisterDTO userRegisterDTO) {
        if (!validateUserRegister(userRegisterDTO)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        User user = UserAssembler.INSTANCE.toUser(userRegisterDTO);
        return userRepository.addUser(user);
    }

    @Override
    public boolean validateUserRegister(UserRegisterDTO userRegisterDTO) {
        if (userRegisterDTO.getUserPassword().equals(userRegisterDTO.getConfirmedUserPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public UserInfoDTO userLogin(UserLoginDTO userLoginDTO, HttpServletRequest request) {
        User user = UserAssembler.INSTANCE.toUser(userLoginDTO);
        User existUser = userRepository.queryUserByUserAccount(user.getUserAccount());
        if (!checkAccountPasswordRight(user, existUser)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        request.getSession().setAttribute(UserStateEnum.USER_LOGIN_STATE.getState(), existUser);
        return UserAssembler.INSTANCE.toUserInfoDTO(existUser);
    }

    @Override
    public boolean checkAccountPasswordRight(User loginUser, User existUser) {
        if (!loginUser.getUserAccount().equals(existUser.getUserAccount())
                || !loginUser.getUserPassword().equals(existUser.getUserPassword())
        ) {
            return false;
        }
        return true;
    }

    @Override
    public User getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(UserStateEnum.USER_LOGIN_STATE.getState());
        User currentUser = (User) userObj;
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    @Override
    public UserInfoDTO getLoingUser(HttpServletRequest request) {
        User currentUser = getCurrentUser(request);
        return UserAssembler.INSTANCE.toUserInfoDTO(currentUser);
    }

    @Override
    public Integer deactiveUser(HttpServletRequest request) {
        removeSessionUserState(request);
        return 1;
    }

    private static void removeSessionUserState(HttpServletRequest request) {
        request.getSession().removeAttribute(UserStateEnum.USER_LOGIN_STATE.getState());
    }
}

