package com.zzy.piccenter.demos.web.app.service;

import com.zzy.piccenter.demos.web.app.request.UserLoginDTO;
import com.zzy.piccenter.demos.web.app.request.UserRegisterDTO;
import com.zzy.piccenter.demos.web.app.response.UserInfoDTO;
import com.zzy.piccenter.demos.web.domain.user.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    Integer userRegister(UserRegisterDTO userRegisterDTO);

    boolean validateUserRegister(UserRegisterDTO userRegisterDTO);

    UserInfoDTO userLogin(UserLoginDTO userLoginDTO, HttpServletRequest request);

    boolean checkAccountPasswordRight(User loginUser, User existUser);

    User getCurrentUser(HttpServletRequest request);

    UserInfoDTO getLoingUser(HttpServletRequest request);

    Integer deactiveUser(HttpServletRequest request);
}
