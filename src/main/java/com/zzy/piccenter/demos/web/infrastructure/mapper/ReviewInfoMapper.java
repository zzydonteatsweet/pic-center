package com.zzy.piccenter.demos.web.infrastructure.mapper;

import com.zzy.piccenter.demos.web.infrastructure.po.ReviewInfoPO;

public interface ReviewInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReviewInfoPO record);

    int insertSelective(ReviewInfoPO record);

    ReviewInfoPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReviewInfoPO record);

    int updateByPrimaryKey(ReviewInfoPO record);
}