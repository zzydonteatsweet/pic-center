package com.zzy.piccenter.demos.web.domain.user;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2024-12-19 00:10
 **/
@Data
public class User {
    private Long id;

    private String userAccount;

    private String userPassword;

    private String userName;

    private String userAvatar;

    private String userProfile;

    private String userRole;

    private Date editTime;

    private Date createTime;

    private Date updateTime;

    private Date vipExpireTime;

    private String vipCode;

    private Long vipNumber;

    private String shareCode;

    private Long inviteUser;
}
