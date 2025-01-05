package com.zzy.piccenter.demos.web.infrastructure.mapper;

import com.zzy.piccenter.demos.web.domain.picture.Picture;
import com.zzy.piccenter.demos.web.infrastructure.po.PicturePO;

import java.util.List;

public interface PictureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PicturePO record);

    int insertSelective(PicturePO record);

    PicturePO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PicturePO record);

    List<PicturePO> selectSelective(PicturePO picturePO);

    List<PicturePO> selectSelectiveFuzzily(PicturePO picturePO);

    int updateByPrimaryKey(PicturePO record);
}