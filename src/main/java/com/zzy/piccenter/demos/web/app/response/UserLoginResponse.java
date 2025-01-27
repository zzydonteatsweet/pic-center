package com.zzy.piccenter.demos.web.app.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-01-18 12:49
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserLoginResponse {
    private String userAccount;

    private String userName;

    private String userAvatar;

    private String userProfile;

    private String inviteUser;

    private String userRole;

    private String token;
}
