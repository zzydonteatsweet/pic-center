package com.zzy.piccenter.demos.web.app.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2024-12-18 23:55
 **/
@Data
public class UserRegisterDTO {
    @NotNull
    @Size(max = 300)
    private String userAccount;

    private String userName;

    private String userProfile;

    private String userAvatar;

    @NotNull
    @Size(max = 300)
    private String userPassword;
    @Size(max = 300)
    private String confirmedUserPassword;
}
