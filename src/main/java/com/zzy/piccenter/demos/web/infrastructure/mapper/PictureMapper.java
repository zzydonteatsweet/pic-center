package com.zzy.piccenter.demos.web.infrastructure.mapper;

import com.zzy.piccenter.demos.web.infrastructure.po.PicturePO;

public interface PictureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PicturePO record);

    int insertSelective(PicturePO record);

    PicturePO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PicturePO record);

    int updateByPrimaryKey(PicturePO record);
}