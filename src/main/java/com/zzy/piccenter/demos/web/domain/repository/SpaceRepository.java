package com.zzy.piccenter.demos.web.domain.repository;

import com.zzy.piccenter.demos.web.domain.space.Space;

import java.util.List;

public interface SpaceRepository {
    int addSpace(Space space);

    List<Space> findSpaceSelectively(Space space);

    int deleteSpaceByPrimaryKey(Long id);
}
