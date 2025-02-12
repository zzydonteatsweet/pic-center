package com.zzy.piccenter.demos.web.infrastructure.common.converter;

import com.zzy.piccenter.demos.web.domain.space.Space;
import com.zzy.piccenter.demos.web.infrastructure.po.SpacePO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SpaceConverter {
    SpaceConverter INSTANCE = Mappers.getMapper(SpaceConverter.class);

    SpacePO toSpacePO(Space space);

    Space toSpace(SpacePO spacePO);

    List<Space> toSpace(List<SpacePO> spacePO);
}
