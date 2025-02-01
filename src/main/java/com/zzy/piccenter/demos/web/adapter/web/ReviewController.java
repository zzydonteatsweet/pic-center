package com.zzy.piccenter.demos.web.adapter.web;

import com.zzy.piccenter.demos.web.app.request.cmd.ReviewPicCmd;
import com.zzy.piccenter.demos.web.app.response.BaseResponse;
import com.zzy.piccenter.demos.web.app.response.UserInfoDTO;
import com.zzy.piccenter.demos.web.app.service.ReviewService;
import com.zzy.piccenter.demos.web.app.service.UserService;
import com.zzy.piccenter.demos.web.infrastructure.common.utils.ResultUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-01-30 20:38
 **/
@RestController
@RequestMapping("/review")
public class ReviewController {
    @Resource
    private UserService userService;

    @Resource
    private ReviewService reviewService;

    @PostMapping("/picture")
    public BaseResponse<String> reviewPicture(@RequestBody @Validated ReviewPicCmd cmd, HttpServletRequest request) {
        UserInfoDTO user = userService.getLoingUser(request);
        reviewService.reviewPicture(cmd, user);
        return ResultUtils.success("success");
    }
}
