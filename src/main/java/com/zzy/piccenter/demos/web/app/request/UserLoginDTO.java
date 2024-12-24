package com.zzy.piccenter.demos.web.app.request;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2024-12-18 23:56
 **/
@Data
public class UserLoginDTO {
    @NotNull
    private String userAccount;
    @NotNull
    private String userPassword;
}
