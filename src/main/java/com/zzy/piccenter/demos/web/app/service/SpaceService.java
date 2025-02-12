package com.zzy.piccenter.demos.web.app.service;

import com.zzy.piccenter.demos.web.app.request.cmd.SpaceInfoCmd;
import com.zzy.piccenter.demos.web.app.response.UserInfoDTO;

public interface SpaceService {
    void addPrivateSpace(UserInfoDTO userInfoDTO, SpaceInfoCmd cmd);

    void deletePrivateSpace(UserInfoDTO userInfoDTO);
}
