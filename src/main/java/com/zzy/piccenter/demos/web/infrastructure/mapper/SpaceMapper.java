package com.zzy.piccenter.demos.web.infrastructure.mapper;

import com.zzy.piccenter.demos.web.infrastructure.po.SpacePO;

import java.util.List;

public interface SpaceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpacePO record);

    int insertSelective(SpacePO record);

    SpacePO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpacePO record);

    int updateByPrimaryKey(SpacePO record);

    List<SpacePO> selectSelective(SpacePO record);
}