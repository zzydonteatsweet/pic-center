package com.zzy.piccenter.demos.web.infrastructure.service;

import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.zzy.piccenter.demos.web.app.request.cmd.SpaceInfoCmd;
import com.zzy.piccenter.demos.web.app.response.UserInfoDTO;
import com.zzy.piccenter.demos.web.app.service.SpaceService;
import com.zzy.piccenter.demos.web.domain.common.enums.SpaceLevelEnum;
import com.zzy.piccenter.demos.web.domain.common.enums.SpaceTypeEnum;
import com.zzy.piccenter.demos.web.domain.repository.SpaceRepository;
import com.zzy.piccenter.demos.web.domain.space.Space;
import com.zzy.piccenter.demos.web.infrastructure.common.exception.BusinessException;
import com.zzy.piccenter.demos.web.infrastructure.common.exception.ErrorCode;
import com.zzy.piccenter.demos.web.infrastructure.common.utils.EnumUtils;
import com.zzy.piccenter.demos.web.infrastructure.common.utils.ThrowUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-02-07 23:36
 **/
@Service
public class SpaceServiceImpl implements SpaceService {
    @Resource
    private SpaceRepository spaceRepository;

    @Override
    public void addPrivateSpace(UserInfoDTO userInfoDTO, SpaceInfoCmd cmd) {
        Space queryBean = Space.builder().id(userInfoDTO.getId()).isDelete((byte)0).build();
        List<Space> res = spaceRepository.findSpaceSelectively(queryBean);
        ThrowUtils.throwIf(CollectionUtils.isNotEmpty(res), new BusinessException(ErrorCode.OPERATION_ERROR, "用户" + userInfoDTO.getUserAccount() + "已经创建过私人空间了"));
        Space createBean = Space.builder()
                .userId(userInfoDTO.getId())
                .spaceName(cmd.getSpaceName())
                .spaceLevel(cmd.getSpaceLevel())
                .spaceType(SpaceTypeEnum.PRIVATE.getSpaceType())
                .maxCount(EnumUtils.getEnumById(SpaceLevelEnum.class, cmd.getSpaceLevel()).getMaxCount())
                .maxSize(EnumUtils.getEnumById(SpaceLevelEnum.class, cmd.getSpaceLevel()).getMaxSize())
                .build();
        spaceRepository.addSpace(createBean);
    }

    @Override
    public void deletePrivateSpace(UserInfoDTO userInfoDTO) {
        Space queryBean = Space.builder()
                .userId(userInfoDTO.getId())
                .spaceType(SpaceTypeEnum.PRIVATE.getSpaceType())
                .isDelete((byte) 0)
                .build();
        List<Space> spaces = spaceRepository.findSpaceSelectively(queryBean);
        ThrowUtils.throwIf(CollectionUtils.isEmpty(spaces), new BusinessException(ErrorCode.OPERATION_ERROR, String.format("用户%s没有创建私人空间", userInfoDTO.getUserAccount())));
        spaceRepository.deleteSpaceByPrimaryKey(spaces.get(0).getId());
    }
}
