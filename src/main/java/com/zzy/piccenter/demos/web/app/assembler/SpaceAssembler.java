package com.zzy.piccenter.demos.web.app.assembler;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpaceAssembler {
    SpaceAssembler INSTANCE = Mappers.getMapper(SpaceAssembler.class);

}
