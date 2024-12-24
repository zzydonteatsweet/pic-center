package com.zzy.piccenter.demos.web.app.repository;

import com.zzy.piccenter.demos.web.domain.user.User;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2024-12-19 00:00
 **/
public interface UserRepository {
    int addUser(User user);

    User queryUserByUserAccount(String userAccount);
}
