package com.zzy.piccenter.demos.web.adapter.web;

import com.zzy.piccenter.demos.web.app.dto.SpaceInfoDTO;
import com.zzy.piccenter.demos.web.app.request.cmd.SpaceInfoCmd;
import com.zzy.piccenter.demos.web.app.response.BaseResponse;
import com.zzy.piccenter.demos.web.app.response.UserInfoDTO;
import com.zzy.piccenter.demos.web.app.service.SpaceService;
import com.zzy.piccenter.demos.web.app.service.UserService;
import com.zzy.piccenter.demos.web.infrastructure.common.utils.ResultUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-02-07 23:35
 **/
@RestController
public class SpaceController {
    @Resource
    private UserService userService;

    @Resource
    private SpaceService spaceService;

    @PostMapping("/space")
    public BaseResponse<String> addSpace(HttpServletRequest request, SpaceInfoCmd spaceInfoCmd) {
        UserInfoDTO userInfoDTO = userService.getLoingUser(request);
        spaceService.addPrivateSpace(userInfoDTO, spaceInfoCmd);
        return ResultUtils.success("success");
    }

    @DeleteMapping("/space")
    public BaseResponse<String> deleteSpace(HttpServletRequest request) {
         UserInfoDTO userInfoDTO = userService.getLoingUser(request);
         spaceService.deletePrivateSpace(userInfoDTO);
         return ResultUtils.success("success");
    }
}
