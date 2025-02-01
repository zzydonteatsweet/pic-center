package com.zzy.piccenter.demos.web.app.assembler;

import com.zzy.piccenter.demos.web.app.request.UserLoginDTO;
import com.zzy.piccenter.demos.web.app.request.UserRegisterDTO;
import com.zzy.piccenter.demos.web.app.response.UserInfoDTO;
import com.zzy.piccenter.demos.web.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author T041018
 */
@Mapper
public interface UserAssembler {
    UserAssembler INSTANCE = Mappers.getMapper(UserAssembler.class);

    User toUser(UserRegisterDTO dto);

    User toUser(UserLoginDTO dto);

    UserInfoDTO toUserInfoDTO(User user);

    UserInfoDTO toUserLoginResponse(User user);

    default UserInfoDTO toUserLoginResponse(User user, String token) {
        UserInfoDTO userInfoDTO = toUserLoginResponse(user);
        userInfoDTO.setToken(token);
        return userInfoDTO;
    }
}
