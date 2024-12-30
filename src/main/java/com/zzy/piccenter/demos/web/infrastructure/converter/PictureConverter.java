package com.zzy.piccenter.demos.web.infrastructure.converter;

import com.zzy.piccenter.demos.web.domain.picture.Picture;
import com.zzy.piccenter.demos.web.infrastructure.po.PicturePO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-01-02 12:27
 **/
@Mapper
public interface PictureConverter {
    PictureConverter INSTANCE = Mappers.getMapper(PictureConverter.class);

    @Mapping(target = "tags", expression = "java(com.alibaba.fastjson2.JSON.toJSONString(picture.getTags()))")
    PicturePO toPicturePO(Picture picture);

    @Mapping(target = "tags", expression = "java(com.alibaba.fastjson2.JSON.parseArray(po.getTags(), String.class))")
    Picture toPicture(PicturePO po);
}
