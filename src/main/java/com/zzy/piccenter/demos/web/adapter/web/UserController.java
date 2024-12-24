package com.zzy.piccenter.demos.web.adapter.web;

import cn.hutool.system.UserInfo;
import com.zzy.piccenter.demos.web.app.dto.UserInfoDTO;
import com.zzy.piccenter.demos.web.app.request.UserLoginDTO;
import com.zzy.piccenter.demos.web.app.request.UserRegisterDTO;
import com.zzy.piccenter.demos.web.app.response.BaseResponse;
import com.zzy.piccenter.demos.web.app.service.UserService;
import com.zzy.piccenter.demos.web.infrastructure.common.utils.ResultUtils;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2024-12-18 23:56
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Integer> userRegister(@RequestBody @Validated UserRegisterDTO userRegisterDTO) {
        return ResultUtils.success(userService.userRegister(userRegisterDTO));
    }

    @PostMapping("/login")
    public BaseResponse<UserInfoDTO> userLogin(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request) {
        UserInfoDTO userInfoDTO = userService.userLogin(userLoginDTO, request);
        return ResultUtils.success(userInfoDTO);
    }

    @GetMapping("/get-info")
    public BaseResponse<UserInfoDTO> getUserInfo(HttpServletRequest request) {
        return ResultUtils.success(userService.getLoingUser(request));
    }

    @GetMapping("/deactive")
    public BaseResponse<Integer> deactiveUser(String userAccount, HttpServletRequest request) {
        return ResultUtils.success(userService.deactiveUser(userAccount, request));
    }
}
