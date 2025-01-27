package com.zzy.piccenter.demos.web.domain.user;

import com.alibaba.fastjson2.JSON;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2024-12-19 00:10
 **/
@Data
@Builder
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

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
