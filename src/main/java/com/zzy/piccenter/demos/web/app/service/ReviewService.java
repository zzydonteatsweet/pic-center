package com.zzy.piccenter.demos.web.app.service;


import com.zzy.piccenter.demos.web.app.request.cmd.ReviewPicCmd;
import com.zzy.piccenter.demos.web.app.response.UserInfoDTO;

public interface ReviewService {
    void reviewPicture(ReviewPicCmd cmd, UserInfoDTO userInfo);
}
