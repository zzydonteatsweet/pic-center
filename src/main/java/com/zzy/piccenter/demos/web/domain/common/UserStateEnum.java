package com.zzy.piccenter.demos.web.domain.common;

import lombok.Getter;

/**
 * @author T041018
 */

@Getter
public enum UserStateEnum {
    USER_LOGIN_STATE("user_login"),
    DEFAULT_ROLE("user"),
    ADMIN_ROLE("admin");


    private String state;

    UserStateEnum(String state) {
        this.state = state;
    }

    /**
     * 用户登录态键
     */
//    String USER_LOGIN_STATE ="user_login";

    //  region 权限

    /**
     * 默认角色
     */
//    String DEFAULT_ROLE = "user";

    /**
     * 管理员角色
     */
//    String ADMIN_ROLE = "admin";


}
