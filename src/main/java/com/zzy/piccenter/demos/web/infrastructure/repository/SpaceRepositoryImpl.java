package com.zzy.piccenter.demos.web.infrastructure.repository;

import com.zzy.piccenter.demos.web.domain.repository.SpaceRepository;
import com.zzy.piccenter.demos.web.domain.space.Space;
import com.zzy.piccenter.demos.web.infrastructure.common.converter.SpaceConverter;
import com.zzy.piccenter.demos.web.infrastructure.mapper.SpaceMapper;
import com.zzy.piccenter.demos.web.infrastructure.po.SpacePO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-02-07 23:38
 **/
@Repository
public class SpaceRepositoryImpl implements SpaceRepository {
    @Resource
    private SpaceMapper spaceMapper;

    @Override
    public int addSpace(Space space) {
        SpacePO po = SpaceConverter.INSTANCE.toSpacePO(space);
        po.setIsDelete((byte)0);
        return spaceMapper.insertSelective(po);
    }

    @Override
    public List<Space> findSpaceSelectively(Space space) {
        SpacePO bean = SpaceConverter.INSTANCE.toSpacePO(space);
        List<SpacePO> spacePO = spaceMapper.selectSelective(bean);
        return SpaceConverter.INSTANCE.toSpace(spacePO);
    }

    @Override
    public int deleteSpaceByPrimaryKey(Long id) {
        SpacePO deleteBean = SpacePO.builder().id(id).isDelete((byte)1).build();
        return spaceMapper.updateByPrimaryKeySelective(deleteBean);
    }

}
